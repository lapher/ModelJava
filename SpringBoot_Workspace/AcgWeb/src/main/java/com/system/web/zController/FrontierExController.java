package com.system.web.zController;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.system.web.bean.FFClub;
import com.system.web.mapper.CommonUseMapperService;
import com.system.web.mapper.FFClubService;
import com.system.web.mapper.FrontierBookService;



@Controller
@RequestMapping("/FrontierEx") // 統一大寫 以下小寫
public class FrontierExController {

//	private static final Logger log = LogManager.getLogger(Teller_InformationController.class);

	@Autowired
	FrontierBookService service;
	@Autowired
	CommonUseMapperService commonUseService;
	@Autowired
	private FFClubService fFClubService;
	
	
	@Autowired
	private MessageSource messageSource;
	Locale locale = LocaleContextHolder.getLocale();

	// FF40
	@GetMapping("/ff40")
	public String mainPage(Model model) {
		model.addAttribute("mainPage", "frontierEx/ff40");	// 統一小寫
		return "model/dashboard";
	}
	
	// SelectAll
	@GetMapping(value = "/getBeanAll")
	public @ResponseBody Map<String, Object> getBeanAll() {
		Map<String, Object> map = new HashMap<>();

		// SQL
		List<FFClub> beanAll = fFClubService.selectAll();
		map.put("beanAll", beanAll);

		return map;
	}
	

}
