package com.congruent.compulaw.web.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class HomeController {
	
	private final static boolean accountStatus = true;
	private final static boolean userSession = false;

	final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model uiModel) {
		// Check if user has transaction
		uiModel.addAttribute("accountStatus", accountStatus);
		uiModel.addAttribute("userSession", userSession);
		return "home";
	}
}
