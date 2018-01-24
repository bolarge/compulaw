package com.congruent.compulaw.domain;

import com.congruent.compulaw.enums.SubscriptionStatus;
import com.congruent.compulaw.enums.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "TRANSACTION")
@Entity
public class Transaction {

	//@JsonIgnore
	@JsonIgnore
	@EmbeddedId
	private UserSubscriptionId id = new UserSubscriptionId();

	@Column(name = "APPROVED_BY", length = 25)
	private String approvedBy;

	@Column(name = "DATE_CREATED")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@org.springframework.format.annotation.DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private DateTime dateCreated = new DateTime();

	@Column(name = "DATE_APPROVED")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@org.springframework.format.annotation.DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private DateTime dateApproved;

	@Column(name = "DUE_DATE")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@org.springframework.format.annotation.DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private DateTime dueDate;

	@NotNull
	@Column(name = "TELLERDATE")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@org.springframework.format.annotation.DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private DateTime tellerDate;

	@Column(name = "ENABLED")
	private boolean enabled = false;

	@NotNull
	@Column(name = "TELLER", length = 10)
	private String tellerNumber;

	@NotNull
	@Column(name = "BANK")
	private String bankName;

	@NotNull
	@Column(name = "AMOUNT")
	private String amountPaid;

	@NotNull
	@Column(name = "ITEM_NAME")
	private String itemName;

	@NotNull
	@Column(name = "SUBSCRIBER")
	private String subscriber;

	@NotNull
	@Column(name = "TRANSACTION_ID")
	private String transactionId;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private SubscriptionStatus status = SubscriptionStatus.SUBSCRIBED;

	@Column(name = "SUBSCR_TYPE")
	@Enumerated(EnumType.STRING)
	private SubscriptionType subscriptionType;
	
	@Column(name = "PAYER_PHONE")
	private String payerPhone;

	@Column(name = "VERSION")
	private int version = 0;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USER_ID", insertable = false, updatable = false)
	private User user;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "SUBSCRIPTION_ID", insertable = false, updatable = false)
	private Subscription subscription;

	public Transaction() {
	}

	public Transaction(String approvedBy, User user, Subscription subscription,
			DateTime dateCreated, DateTime dateApproved, DateTime tellerDate,
			DateTime dueDate, String tellerNumber, String amountPaid,
			String bankName, boolean enabled, int version, String itemName,
			String subscriber, String transactionId, SubscriptionStatus status,
			SubscriptionType subType) {
		this.approvedBy = approvedBy;

		this.user = user;
		this.subscription = subscription;

		this.dateCreated = dateCreated;
		this.dateApproved = dateApproved;
		this.tellerDate = tellerDate;
		this.dueDate = dueDate;

		this.tellerNumber = tellerNumber;
		this.amountPaid = amountPaid;
		this.bankName = bankName;
		this.enabled = enabled;
		this.version = version;

		this.itemName = itemName;
		this.subscriber = subscriber;
		this.transactionId = transactionId;
		this.status = status;
		this.subscriptionType = subType;

		this.id.userId = user.getId();
		this.id.subscriptionId = subscription.getId();

		user.getTransactions().add(this);
		subscription.getTransactions().add(this);
	}

	@JsonIgnore
	public UserSubscriptionId getId() {
		return this.id;
	}

	public void setId(UserSubscriptionId id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Subscription getSubscription() {
		return this.subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public DateTime getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(DateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public DateTime getDateApproved() {
		return this.dateApproved;
	}

	public void setDateApproved(DateTime dateApproved) {
		this.dateApproved = dateApproved;
	}

	public DateTime getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getTellerNumber() {
		return this.tellerNumber;
	}

	public void setTellerNumber(String tellerNumber) {
		this.tellerNumber = tellerNumber;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public DateTime getTellerDate() {
		return this.tellerDate;
	}

	public void setTellerDate(DateTime tellerDate) {
		this.tellerDate = tellerDate;
	}
	
	@Transient
	public String getDateCreatedString() {
		String dateCreatedString = "";
		if (this.dateCreated != null)
			dateCreatedString = org.joda.time.format.DateTimeFormat.forPattern(
					"dd-MM-yyyy").print(this.dateCreated);
		return dateCreatedString;
	}

	@Transient
	public String getTellerDateString() {
		String tellerDateString = "";
		if (this.tellerDate != null)
			tellerDateString = org.joda.time.format.DateTimeFormat.forPattern(
					"dd-MM-yyyy").print(this.tellerDate);
		return tellerDateString;
	}

	@Transient
	public String getApproveDateString() {
		String dateApprovedString = "";
		if (this.dateApproved != null)
			dateApprovedString = org.joda.time.format.DateTimeFormat
					.forPattern("dd-MM-yyyy").print(this.dateApproved);
		return dateApprovedString;
	}

	@Transient
	public String getDueDateString() {
		String dueDateString = "";
		if (this.dueDate != null)
			dueDateString = org.joda.time.format.DateTimeFormat.forPattern(
					"dd-MM-yyyy").print(this.dueDate);
		return dueDateString;
	}

	public String getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSubscriber() {
		return this.subscriber;
	}

	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public SubscriptionStatus getStatus() {
		return this.status;
	}

	public String getPayerPhone() {
		return payerPhone;
	}

	public void setPayerPhone(String payerPhone) {
		this.payerPhone = payerPhone;
	}

	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}

	public SubscriptionType getSubscriptionType() {
		return this.subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String toString() {
		return "UserSubscription [getTellerNumber()=" + getTellerNumber()
				+ ", getBankName()=" + getBankName()
				+ ", getTellerDateString()=" + getTellerDateString()
				+ ", getAmountPaid()=" + getAmountPaid() + ", getItemName()="
				+ getItemName() + ", getSubscriber()=" + getSubscriber()
				+ ", getApprovedBy()=" + getApprovedBy()
				+ ", getApprovedDate()=" + getDateApproved() + ", IsEnabled()="
				+ isEnabled() + ", getVersion()=" + getVersion()
				+ ", getUser()=" + getUser() + ", getSubscriber()="
				+ getSubscriber() + "]";
	}

	//@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
	@Embeddable
	public static class UserSubscriptionId implements Serializable {
		private static final long serialVersionUID = 1L;

		@Column(name = "USER_ID")
		private Long userId;

		@Column(name = "SUBSCRIPTION_ID")
		private Long subscriptionId;

		public UserSubscriptionId() {
		}

		public UserSubscriptionId(Long userId, Long subscriptionId) {
			this.userId = userId;
			this.subscriptionId = subscriptionId;
		}

		public boolean equals(Object o) {
			if ((o != null) && ((o instanceof UserSubscriptionId))) {
				UserSubscriptionId that = (UserSubscriptionId) o;

				return (this.userId.equals(that.userId))
						&& (this.subscriptionId.equals(that.subscriptionId));
			}
			return false;
		}
	}
}