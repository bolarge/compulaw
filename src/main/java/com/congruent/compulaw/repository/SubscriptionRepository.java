package com.congruent.compulaw.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.congruent.compulaw.domain.Subscription;

public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, Long>{

}
