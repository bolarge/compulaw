package com.congruent.compulaw.web.editor;

import java.beans.PropertyEditorSupport;

import com.congruent.compulaw.domain.Subscription;
import com.congruent.compulaw.service.SubscriptionService;

public class SubscriptionTypeEditor extends PropertyEditorSupport {

	private SubscriptionService subscriptionService;	

	public SubscriptionTypeEditor() {
		super();
	}

	public SubscriptionTypeEditor(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long subscriptionId = Long.parseLong(text);
		Subscription subscription = subscriptionService
				.findById(subscriptionId);
		setValue(subscription);
	}

}
