package com.congruent.compulaw.web.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class PriceController {
	
	final Logger logger = LoggerFactory.getLogger(PriceController.class);
	  
	private final static boolean accountStatus = true;
	private final static boolean userSession = false;
	
	@RequestMapping(value = "price", method = RequestMethod.GET)
	public String price(Model uiModel) {
		uiModel.addAttribute("accountStatus", accountStatus);
		uiModel.addAttribute("userSession", userSession);
		return "price";
	}
	

}
