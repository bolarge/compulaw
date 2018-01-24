package com.congruent.compulaw.domain;

import org.joda.time.DateTime;

public class SubscriptionDTO {
	
	private User user;
	private Subscription subscription;
	private String approvedBy;
	private DateTime dateCreated;
	private DateTime dateApproved;
	private DateTime dueDate;
	private boolean enabled;
	private String tellerNumber;
	private String bankName;
	private String amountPaid;
	private DateTime tellerDate;
	private String itemName;
	private String subscriber;
	private String transactionId;
	
	public SubscriptionDTO(){}
	
	public SubscriptionDTO(User user, Subscription subscription,
			String approvedBy, DateTime dateCreated, DateTime dateApproved,
			DateTime dueDate, boolean enabled, String tellerNumber) {
		//super();
		this.user = user;
		this.subscription = subscription;
		this.approvedBy = approvedBy;
		this.dateCreated = dateCreated;
		this.dateApproved = dateApproved;
		this.dueDate = dueDate;
		this.enabled = enabled;
		this.tellerNumber = tellerNumber;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public DateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(DateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public DateTime getDateApproved() {
		return dateApproved;
	}
	public void setDateApproved(DateTime dateApproved) {
		this.dateApproved = dateApproved;
	}
	public DateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getTellerNumber() {
		return tellerNumber;
	}
	public void setTellerNumber(String tellerNumber) {
		this.tellerNumber = tellerNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public DateTime getTellerDate() {
		return tellerDate;
	}

	public void setTellerDate(DateTime tellerDate) {
		this.tellerDate = tellerDate;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public String toString() {
		return "SubscriptionDTO [user=" + user + ", subscription="
				+ subscription + ", dateCreated=" + dateCreated
				+ ", tellerNumber=" + tellerNumber + ", bankName=" + bankName
				+ "]";
	}
	
	

}
