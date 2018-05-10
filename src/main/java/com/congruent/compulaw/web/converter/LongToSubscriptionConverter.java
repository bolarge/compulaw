/*package com.congruent.compulaw.web.converter;

import com.congruent.compulaw.domain.Subscription;
import com.congruent.compulaw.service.SubscriptionService;

import org.springframework.core.convert.converter.Converter;

*//**
 * A editor which allows the translation between {@link Long} and
 * {@link Product}.
 * 
 * 
 * @author Stefan Schmidt
 * @since 0.3
 * 
 *//*
public class LongToSubscriptionConverter implements Converter<Long, Subscription> {

	private SubscriptionService subscriptionService;

	public LongToSubscriptionConverter(SubscriptionService productService) {
		this.subscriptionService = productService;
	}	
	

	public LongToSubscriptionConverter() {
		super();
	}


	@Override
	public Subscription convert(Long target)
			 {
		Subscription subscription = subscriptionService.findById(target);
		return subscription;
	}

	public Object convertSourceToTargetClass(Object source, Class<Subscription> targetClass)
			{
		Long longSource = (Long) source;
		if (longSource != null && longSource > 0) {
			return subscriptionService.findById(longSource);
		} else {
			return null;
		}
	}

	public Class<Long> getSourceClass() {
		return Long.class;
	}

	public Class<Subscription> getTargetClass() {
		return Subscription.class;
	}
}
*/