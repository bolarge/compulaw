package com.congruent.compulaw.repository;

import org.springframework.data.repository.CrudRepository;

import com.congruent.compulaw.domain.Subscription;

public interface SubscriptionItemRepository extends CrudRepository<Subscription, Long>{

}
