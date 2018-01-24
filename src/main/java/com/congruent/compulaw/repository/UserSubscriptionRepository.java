/*package com.congruent.compulaw.repository;

import org.springframework.data.repository.PagingAndSortingRepository;


import com.congruent.compulaw.domain.UserSubscription;
import com.congruent.compulaw.domain.UserSubscriptionPK;

public interface UserSubscriptionRepository extends PagingAndSortingRepository<UserSubscription, UserSubscriptionPK>{

	//public UserSubscription findByTransactionId(Long transactionid);
	public UserSubscription findByTellerNumber(String paramString);

	  public UserSubscription findBySubscriber(String paramString);

	  public UserSubscription findByTransactionId(String paramString);
}*/