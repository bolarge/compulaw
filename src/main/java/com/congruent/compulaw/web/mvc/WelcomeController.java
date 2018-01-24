package com.congruent.compulaw.web.mvc;

import java.util.Locale;

import com.congruent.compulaw.domain.User;

import com.congruent.compulaw.service.PersonService;
import com.congruent.compulaw.service.TransactionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	@Autowired
	private PersonService userDetailsService;

	@Autowired
	private TransactionService transactionService;
	private static Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	private final static boolean accountStatus = true;

	@RequestMapping(method = RequestMethod.GET, value = "public/subscr_welcome")
	public String showWelcomePage(Model uiModel) {
		logger.debug("Page Request: /public/subscr_welcome.do");
		uiModel.addAttribute("accountStatus", accountStatus);
		return "public/s_welcome";
	}

	@RequestMapping(method = RequestMethod.GET, value = "sys_auth/welcome")
	public String showHomePage(Model uiModel, Locale locale) {
		logger.info("Processing login............");
		User user = this.userDetailsService.getCurrentUser();
		String link = null;
		//
		if (user == null) {
			link = "sys_auth/create";
		}
		else{
			//Set<UserRole> userType1 = user.getUserRoles(); //Check User Profile
			//logger.info("User profile is: " + userType1.contains("ROLE_ADMIN"));
			String userType = user.getUserType();
			boolean accountStatus;
			if (userType.equalsIgnoreCase("A")) {
			//if (userType1.contains("ROLE_ADMIN")) {
				accountStatus = true;
				String currentUser = user.getFirstName();
				logger.info("Signed in user is" + currentUser);
				uiModel.addAttribute("accountStatus", accountStatus);
				uiModel.addAttribute("currentUser", user.getFirstName());
				link = "public/a_welcome"; // Administrator Welcome
			} else if (userType.equalsIgnoreCase("S")) {
				//
				uiModel.addAttribute("currentUser", user.getFirstName());
				accountStatus = this.transactionService
						.checkTransactionAccountStatus(user);// Check account
																// status
				uiModel.addAttribute("accountStatus", accountStatus);
				if (accountStatus) {
					link = "public/sb_keyw/search";// "public/s_welcome";
				} else {
					link = "sys_auth/error/inactive";
					user = null;
				}
			} else {
				//link = "sys_auth/error/403";
				link = "sys_auth/create";
			}
		} 
		return link;
	}

	@RequestMapping(method = RequestMethod.GET, value = "public/admin_welcome")
	public String showAdminWelcomePage(Model uiModel) {
		logger.debug("Page Request: /public/admin_welcome.do");
		uiModel.addAttribute("accountStatus", accountStatus);
		return "public/a_welcome";
	}
}