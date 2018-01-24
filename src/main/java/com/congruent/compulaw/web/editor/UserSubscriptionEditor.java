/*package com.congruent.compulaw.web.editor;

import java.beans.PropertyEditorSupport;

import com.congruent.compulaw.domain.UserSubscription;
import com.congruent.compulaw.service.UserSubscriptionService;

public class UserSubscriptionEditor extends PropertyEditorSupport{
	
	private UserSubscriptionService uSubService;
	
	public UserSubscriptionEditor(){}
	
	public UserSubscriptionEditor(UserSubscriptionService uSubService){
		this.uSubService = uSubService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Long txId = Long.parseLong(text);
		UserSubscription uSub = uSubService.findById(txId);
		setValue(uSub);
	}

}
*/