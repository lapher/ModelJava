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
@RequestMapping("/FrontierEx") // 統一大寫 以下小寫
public class FrontierExController {

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

	// FF40
	@GetMapping("/ff40")
	public String mainPage(Model model) {
		model.addAttribute("mainPage", "frontierEx/ff40");	// 統一小寫
		return "model/dashboard";
	}

	

}
