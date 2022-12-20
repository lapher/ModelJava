package com.system.web.zController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.system.aShiro.bean.Account;
import com.system.web.bean.ColOptions;
import com.system.web.bean.FrontierBook;
import com.system.web.mapper.CommonUseMapperService;
import com.system.web.mapper.FrontierBookService;



@Controller
@RequestMapping("/FrontierBook") // 統一大寫 以下小寫
public class FrontierBookController {

//	private static final Logger log = LogManager.getLogger(Teller_InformationController.class);

	@Autowired
	FrontierBookService service;
	@Autowired
	CommonUseMapperService commonUseService;

	@Autowired
	private MessageSource messageSource;
	Locale locale = LocaleContextHolder.getLocale();

	// 資料補登入
	@GetMapping("/")
	public String mainPage(Model model) {
		model.addAttribute("mainPage", "page/FrontierBook");	// 統一小寫
		return "model/dashboard";
	}

	// 查詢頁面
	@GetMapping("/select_page")
	public String mainSelectPage(Model model) {
		model.addAttribute("mainPage", "select_page/select_FrontierBook");
		return "model/dashboard";
	}

	// SelectAll
	@GetMapping(value = "/getAll")
	public @ResponseBody Map<String, Object> getBeanAll() {
		Map<String, Object> map = new HashMap<>();

		// SQL
		List<FrontierBook> beanAll = service.selectAll();
		map.put("beanAll", beanAll);

		return map;
	}

	// Select
	@PostMapping(value = "/getBean", consumes = "application/json")
	public @ResponseBody Map<String, Object> getBean(@RequestBody FrontierBook bean) {
		Map<String, Object> map = new HashMap<>();

		// SQL
		FrontierBook teller_Information = service.selectID(bean);
		map.put("Bean", teller_Information);

		if (teller_Information == null) {
			map.put("selectAlertMsg", messageSource.getMessage("alert.noData", null, locale));
		}

		return map;
	}

	// Insert
	@PostMapping(value = "/insert", consumes = "application/json")
	public @ResponseBody Map<String, Object> insertBean(@RequestBody FrontierBook bean, HttpSession httpSession) {
		Map<String, Object> map = new HashMap<>();

		// Account Bean
		Account account = (Account) httpSession.getAttribute("account");
//		bean.setMaintainUser(account.getUsername());
//		bean.setMaintainDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
//		bean.setMaintainTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

		// SQL
		Boolean insertResult = service.insert(bean);
		if (!insertResult) {
			map.put("editAlertMsg", messageSource.getMessage("alert.dataExist", null, locale));
			return map;
		}

		// 重新查詢
		FrontierBook select_bean = service.selectID(bean);
		map.put("Bean", select_bean);

		return map;
	}

	// Update
	@PostMapping(value = "/update", consumes = "application/json")
	public @ResponseBody Map<String, Object> updateBean(@RequestBody FrontierBook bean, HttpSession httpSession) {
		Map<String, Object> map = new HashMap<>();

		// Account Bean
		Account account = (Account) httpSession.getAttribute("account");
//		bean.setMaintainUser(account.getUsername());
//		bean.setMaintainDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
//		bean.setMaintainTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

		// SQL
		Boolean updateReslut = service.update(bean);
		if (!updateReslut) {

			map.put("editAlertMsg", messageSource.getMessage("alert.dataExist", null, locale));
			return map;
		}

		// 重新查詢
		FrontierBook select_bean = service.selectID(bean);
		map.put("Bean", select_bean);

		return map;
	}

	// Delete
	@PostMapping(value = "/delete", consumes = "application/json")
	public @ResponseBody Map<String, Object> deleteBean(@RequestBody FrontierBook bean) {
		Map<String, Object> map = new HashMap<>();

		// SQL
		service.delete(bean);

		// 重新查詢
		FrontierBook select_bean = service.selectID(bean);
		map.put("Bean", select_bean);

		return map;
	}

	// select Options
	@GetMapping(value = "/getOptions")
	public @ResponseBody Map<String, Object> getTypes() {
		Map<String, Object> map = new HashMap<>();

		// get All Options
		ColOptions colOptions = new ColOptions();
		colOptions.setTable_Name("Teller_Information");
		List<ColOptions> optionsList = commonUseService.selectColOptions(colOptions);
		String[] optionName = { "real_Teller_Flag", "teller_Auth_Level", "teller_Status", "teller_Type" };

		//
		for (String name : optionName) {
			List<ColOptions> option = new ArrayList<ColOptions>();
			for (ColOptions data : optionsList) {
				if (data.getCol_Name().equals(name)) {
					option.add(data);
				}
			}
			// return
			map.put(name, option);
		}

		return map;
	}

}
