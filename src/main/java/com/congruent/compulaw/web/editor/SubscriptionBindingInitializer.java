package com.congruent.compulaw.web.editor;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.congruent.compulaw.domain.Subscription;
import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.repository.PersonRepository;
import com.congruent.compulaw.service.LawCategoryService;
import com.congruent.compulaw.service.LawSubCategoryService;
import com.congruent.compulaw.service.SubscriptionService;

public class SubscriptionBindingInitializer implements WebBindingInitializer{

	//@Autowired
	private SubscriptionService subscriptionService;
	
	//@Autowired
	private PersonRepository personRepository;
	private LawCategoryService lawCategoryService;
	private LawSubCategoryService lawSubCategoryService;
	
	public SubscriptionBindingInitializer() {
		//super();
	}

	@Autowired
	public SubscriptionBindingInitializer(SubscriptionService subscriptionService, PersonRepository personRepository, 
			LawCategoryService lawCategoryService, LawSubCategoryService lawSubCategoryService) {
		this.subscriptionService = subscriptionService;
		this.personRepository = personRepository;
		this.lawCategoryService = lawCategoryService;
		this.lawSubCategoryService = lawSubCategoryService;
	}
	
	final Logger logger = LoggerFactory.getLogger(SubscriptionBindingInitializer.class);
	
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		String dateFormatPattern = ("yyyy-MM-dd");
		//dateFormat.setLenient(false);
		//binder.registerCustomEditor(DateTime.class, new DateTimeEditorRegistrar(dateFormatPattern));
		binder.registerCustomEditor(Subscription.class, new SubscriptionTypeEditor(subscriptionService));
		binder.registerCustomEditor(User.class, new UserTypeEditor(personRepository));
		binder.registerCustomEditor(LawCategoryEditor.class, new LawCategoryEditor(lawCategoryService));
		binder.registerCustomEditor(LawSubCategoryEditor.class, new LawSubCategoryEditor(lawSubCategoryService));
	}
}
