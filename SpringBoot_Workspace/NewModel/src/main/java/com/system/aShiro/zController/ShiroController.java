package com.system.aShiro.zController;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/")
public class ShiroController {

//	@Autowired
//	LdapUsernamePasswordToken ldapToken;

	@Autowired
	private MessageSource messageSource;
	Locale locale = LocaleContextHolder.getLocale();

	@GetMapping("/Login")
	public String login() {
		return "login/pages-login";
	}

	@GetMapping("/Logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login/pages-login";
	}

	@PostMapping("/toLogin")
	public String toLogin(String username, String password, Model model, HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("帳戶不存在");
			session.setAttribute("alert_isValie", true);
			session.setAttribute("alertMsg", messageSource.getMessage("login.accountOrPasswordError", null, locale));
			return "redirect:/Logout";
		} catch (LockedAccountException e) {
			System.out.println("帳戶不存在");
			session.setAttribute("alert_isValie", true);
			session.setAttribute("alertMsg", messageSource.getMessage("login.accountOrPasswordError", null, locale));
			
			return "redirect:/Logout";
		} catch (IncorrectCredentialsException e) {
			System.out.println("帳戶不存在");
			session.setAttribute("alert_isValie", true);
			session.setAttribute("alertMsg", messageSource.getMessage("login.accountOrPasswordError", null, locale));
			
			return "redirect:/Logout";
		}
		
		return "redirect:/Dashboard";

	}
}
