package com.system.web.zController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
	
	@GetMapping("/Dashboard")
	public String mainPage(Model model) {
		model.addAttribute("mainPage", "page/dashboardMain");
		return "model/dashboard";
	}
	
	
}
