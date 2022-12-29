package com.system.web.zController;

import java.io.File;
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
import com.system.groupBy.mapper.FrontierBookGroupByService;
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
	private FrontierBookGroupByService frontierBookGroupByService;
	
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

	// 圖表
	@GetMapping("/charts")
	public String mainCharts(Model model) {
		model.addAttribute("mainPage", "charts/charts_FrontierBook");
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
	
	// SelectFilterAll
	@PostMapping(value = "/getFilterAll", consumes = "application/json")
	public @ResponseBody Map<String, Object> getFilterAll(@RequestBody FrontierBook bean) {
		Map<String, Object> map = new HashMap<>();

		// SQL
		List<FrontierBook> beanAll = service.selectFilterAll(bean);
		map.put("beanAll", beanAll);

		return map;
	}

	// SelectCharts
	@GetMapping(value = "/getCharts")
	public @ResponseBody Map<String, Object> getBean() {
		Map<String, Object> map = new HashMap<>();
		

		map.put("count", frontierBookGroupByService.count());
		map.put("countByAuther", frontierBookGroupByService.groupByAuther().size());
		map.put("countBySeries", frontierBookGroupByService.groupBySeries().size());
		map.put("countByPrice", frontierBookGroupByService.countByPrice());
		map.put("chartsByAuther", frontierBookGroupByService.chartsByAuther());
		map.put("chartsBySeries", frontierBookGroupByService.chartsBySeries());
		
		
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
		List<FrontierBook> beanAll = service.selectAll();
		map.put("beanAll", beanAll);

		return map;
	}

	// Update
	@PostMapping(value = "/update", consumes = "application/json")
	public @ResponseBody Map<String, Object> updateBean(@RequestBody FrontierBook bean, HttpSession httpSession) {
		Map<String, Object> map = new HashMap<>();

		// Account Bean
//		Account account = (Account) httpSession.getAttribute("account");
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
		List<FrontierBook> beanAll = service.selectAll();
		map.put("beanAll", beanAll);

		return map;
	}

	// Delete
	@PostMapping(value = "/delete", consumes = "application/json")
	public @ResponseBody Map<String, Object> deleteBean(@RequestBody FrontierBook bean) {
		Map<String, Object> map = new HashMap<>();

		// SQL
		String fileName = bean.getPicDir();
		service.delete(bean);
		
		// 檔案移動&刪除
		// 正式用
		String nowpath = System.getProperty("user.dir"); // \apache-tomcat-9.0.45\bin
		String[] split = nowpath.split("bin");
		String picPath = split[0] + "webapps"; // \apache-tomcat-9.0.45\webapps
	
		// 測試用
//		String picPath = "C:\\_java\\git\\ModelJava\\SpringBoot_Workspace\\AcgWeb\\src\\main\\resources\\static";
//		String picPath = "C:\\_Git\\ModelJava\\ModelJava\\SpringBoot_Workspace\\AcgWeb\\src\\main\\resources\\static";
		
		File imageFile = new File(picPath, "/pic/frontierBook/" + fileName); // 儲存位置
		File thumbnailsFile = new File(picPath, "/pic/frontierBook/Thumbnails/" + fileName); // 儲存位置
		File deleteFolder = new File(picPath, "/pic/del/frontierBook"); // 儲存位置
		File deleteFile = new File(picPath, "/pic/del/frontierBook/" + fileName); // 儲存位置
		
		if (!deleteFolder.exists()) { // 檔案不在，則自動建立
			deleteFolder.mkdirs(); 
		}
		
		// 縮圖刪除 原圖搬移
		thumbnailsFile.delete();
		imageFile.renameTo(deleteFile);
		
		// 重新查詢
		List<FrontierBook> beanAll = service.selectAll();
		map.put("beanAll", beanAll);

		return map;
	}

	// select Options
	@GetMapping(value = "/getOptions")
	public @ResponseBody Map<String, Object> getTypes() {
		Map<String, Object> map = new HashMap<>();

		map.put("ffnoOptions", frontierBookGroupByService.groupByFFno());
		map.put("autherOptions", frontierBookGroupByService.groupByAuther());
		map.put("seriesOptions", frontierBookGroupByService.groupBySeries());
		map.put("topicOptions", frontierBookGroupByService.groupByTopic());
		return map;
	}

}
