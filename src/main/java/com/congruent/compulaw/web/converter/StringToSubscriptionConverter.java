/*package com.congruent.compulaw.web.converter;

import com.congruent.compulaw.domain.Subscription;
import com.congruent.compulaw.service.SubscriptionService;

import org.springframework.core.convert.converter.Converter;

*//**
 * A editor which allows the translation between {@link String} and
 * {@link Product}.
 * 
 * 
 * @author Stefan Schmidt
 * @since 0.3
 * 
 *//*
public class StringToSubscriptionConverter implements Converter<String, Subscription> {

	private SubscriptionService subscriptionService;

	public StringToSubscriptionConverter(SubscriptionService productService) {
		this.subscriptionService = productService;
	}	
	

	public StringToSubscriptionConverter() {
		super();
	}


	@Override
	public Subscription convert(String target) {
		String stringSource = target;
		if (stringSource != null && stringSource.length() > 0) {
			return subscriptionService.findById(Long.valueOf(stringSource));
		} else {
			return null;
		}

	}

	public Object convertSourceToTargetClass(Object source, Class<Subscription> targetClass)
			throws Exception {
		if(source != null)
			return ((Subscription) source).toString();
		else return null;
	}

	public Class<String> getSourceClass() {
		return String.class;
	}

	public Class<Subscription> getTargetClass() {
		return Subscription.class;
	}

}
*/