package com.congruent.compulaw.service;

import java.util.List;

import com.congruent.compulaw.domain.Subscription;

public interface SubscriptionService {
	public List<Subscription> findAll();

	  public Subscription findById(Long paramLong);

	  public Subscription findByItemName(String paramString);

	  public Subscription save(Subscription paramSubscription);

	  //public Set<UserSubscription> findAllUserSubscription();

	  //public List<UserSubscription> findAllUserSubscription(List<Subscription> paramList);
}
