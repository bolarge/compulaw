/*package com.congruent.compulaw.domain;

import java.io.Serializable;
import javax.persistence.*;

*//**
 * The primary key class for the USER_SUBSCRIPTION database table.
 * 
 *//*
@Embeddable
public class UserSubscriptionPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private User user;
	private Subscription subscription;

	public UserSubscriptionPK() {
	}

	@ManyToOne
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	public Subscription getSubscription() {
		return this.subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserSubscriptionPK)) {
			return false;
		}
		UserSubscriptionPK castOther = (UserSubscriptionPK)other;
		return 
			this.user.equals(castOther.user)
			&& this.subscription.equals(castOther.subscription);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.user.hashCode();
		hash = hash * prime + this.subscription.hashCode();
		
		return hash;
	}
	
}*/