package com.congruent.compulaw.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class AboutController {
	
	private final static boolean accountStatus = true;
	private final static boolean userSession = false;
	
	@RequestMapping(value = "about", method = RequestMethod.GET)
	public String about(Model uiModel) {
		
		uiModel.addAttribute("accountStatus", accountStatus);
		uiModel.addAttribute("userSession", userSession);
		return "about";
	}

}
