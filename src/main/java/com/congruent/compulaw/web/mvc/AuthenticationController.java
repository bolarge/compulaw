package com.congruent.compulaw.web.mvc;

import java.util.Locale;

import com.congruent.compulaw.service.PersonService;
import com.congruent.compulaw.service.TransactionService;
import com.congruent.compulaw.web.form.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sys_auth/login")
public class AuthenticationController {

	@Autowired
	PersonService personService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	TransactionService transactionService;
	private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model uiModel,  Locale locale) {
		logger.info("Login requested");
		return "login";
	}

	// login fail
	@RequestMapping(method = RequestMethod.GET,  value = "/sys_auth/loginfail" )
	public String loginFail(Model uiModel, Locale locale) {
		logger.info("Login failed detected");
		uiModel.addAttribute("message",new Message("error", messageSource.getMessage(
						"message_login_fail", new Object[] {}, locale)));
		return "sys_auth/loginfail";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sys_auth/error/403")
	public String show403Page(Model model) {
		return "403";
	}

	@RequestMapping(method = RequestMethod.GET, value = "sys_auth/error/inactive_subscr")
	public String showInactiveSubscription(Model model) {
		logger.debug("Page Request: /sys_auth/error/inactive_subscr.do");
		return "sys_auth/error/inactive";
	}
}