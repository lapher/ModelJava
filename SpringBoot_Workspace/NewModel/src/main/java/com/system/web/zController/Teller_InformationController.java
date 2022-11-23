package com.system.web.zController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import com.system.web.bean.Teller_Information;
import com.system.web.mapper.CommonUseMapperService;
import com.system.web.mapper.Teller_InformationMapperService;



@Controller
@RequestMapping("/Teller_Information")
public class Teller_InformationController {

//	private static final Logger log = LogManager.getLogger(Teller_InformationController.class);

	@Autowired
	Teller_InformationMapperService service;
	@Autowired
	CommonUseMapperService commonUseService;

	@Autowired
	private MessageSource messageSource;
	Locale locale = LocaleContextHolder.getLocale();

	// 資料補登入
	@GetMapping("/")
	public String mainPage(Model model) {
		model.addAttribute("mainPage", "page/teller_Information");
		return "model/dashboard";
	}

	// 查詢頁面
	@GetMapping("/select_page")
	public String mainSelectPage(Model model) {
		model.addAttribute("mainPage", "select_page/select_Teller_Information");
		return "model/dashboard";
	}

	// SelectAll
	@GetMapping(value = "/getBeanAll")
	public @ResponseBody Map<String, Object> getBeanAll() {
		Map<String, Object> map = new HashMap<>();

		// SQL
		List<Teller_Information> beanAll = service.selectBeanAll();
		map.put("beanAll", beanAll);

		return map;
	}

	// Select
	@PostMapping(value = "/getTeller_Information", consumes = "application/json")
	public @ResponseBody Map<String, Object> getBean(@RequestBody Teller_Information bean) {
		Map<String, Object> map = new HashMap<>();

		// SQL
		Teller_Information teller_Information = service.selectTeller_InformationMapperByTeller_No(bean.getTeller_No());
		map.put("Bean", teller_Information);

		if (teller_Information == null) {
			map.put("selectAlertMsg", messageSource.getMessage("alert.noData", null, locale));
		}

		return map;
	}

	// Insert
	@PostMapping(value = "/insertTeller_Information", consumes = "application/json")
	public @ResponseBody Map<String, Object> insertBean(@RequestBody Teller_Information bean, HttpSession httpSession) {
		Map<String, Object> map = new HashMap<>();

		// Account Bean
		Account account = (Account) httpSession.getAttribute("account");
		bean.setMaintainUser(account.getUsername());
		bean.setMaintainDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		bean.setMaintainTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

		// SQL
		Boolean insertResult = service.insertTeller_Information(bean);
		if (!insertResult) {
			map.put("editAlertMsg", messageSource.getMessage("alert.dataExist", null, locale));
			return map;
		}

		// 重新查詢
		Teller_Information select_bean = service.selectTeller_InformationMapperByTeller_No(bean.getTeller_No());
		map.put("Bean", select_bean);

		return map;
	}

	// Update
	@PostMapping(value = "/updateTeller_Information", consumes = "application/json")
	public @ResponseBody Map<String, Object> updateBean(@RequestBody Teller_Information bean, HttpSession httpSession) {
		Map<String, Object> map = new HashMap<>();

		// Account Bean
		Account account = (Account) httpSession.getAttribute("account");
		bean.setMaintainUser(account.getUsername());
		bean.setMaintainDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		bean.setMaintainTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

		// SQL
		Boolean updateReslut = service.updateTeller_Information(bean);
		if (!updateReslut) {

			map.put("editAlertMsg", messageSource.getMessage("alert.dataExist", null, locale));
			return map;
		}

		// 重新查詢
		Teller_Information select_bean = service.selectTeller_InformationMapperByTeller_No(bean.getTeller_No());
		map.put("Bean", select_bean);

		return map;
	}

	// Delete
	@PostMapping(value = "/deleteTeller_Information", consumes = "application/json")
	public @ResponseBody Map<String, Object> deleteBean(@RequestBody Teller_Information bean) {
		Map<String, Object> map = new HashMap<>();

		// SQL
		service.deleteTeller_Information(bean.getTeller_No());

		// 重新查詢
		Teller_Information select_bean = service.selectTeller_InformationMapperByTeller_No(bean.getTeller_No());
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
