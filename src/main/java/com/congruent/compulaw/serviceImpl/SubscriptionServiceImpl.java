package com.congruent.compulaw.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congruent.compulaw.domain.Subscription;
import com.congruent.compulaw.repository.SubscriptionRepository;
import com.congruent.compulaw.service.SubscriptionService;
import com.google.common.collect.Lists;

@Service("subscriptionService")
@Repository
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService{
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	final Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public List<Subscription> findAll() {
		//logger.info("Find All Method Called....");
		return Lists.newArrayList(subscriptionRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Subscription findById(Long id) {
		return subscriptionRepository.findOne(id);
	}
	
	@Override
	public Subscription save(Subscription subscription) {	
		return subscriptionRepository.save(subscription);
	}
	
	/*
	@Override
	public List<UserSubscription> findAllUserSubscription(List<Subscription> theSubscriptions) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<UserSubscription> findAllUserSubscription() {
		List<Subscription> subscriptions = this.findAll();
		Set<UserSubscription> transactions = new HashSet<UserSubscription>();
		//
		Iterator<Subscription> subIter = subscriptions.iterator();
		while(subIter.hasNext()){
			Subscription subscription = subIter.next();
			logger.info("Subscription Service is: " + subscription.getItemName());
			transactions = subscription.getUserSubscriptions();
		}
		logger.info("These are " + transactions.size() + " transactions");
		return transactions;		
	}

	@Override
	public void make(SubscriptionDTO subscription) {		
		UserSubscription userSubscription = new UserSubscription();
		Long uuid = (new Random().nextLong()) + 1;		
		//
		userSubscription.setUser(subscription.getUser());
		userSubscription.setSubscription(subscription.getSubscription());
		userSubscription.setItemName(subscription.getSubscription().getItemName());
		userSubscription.setSubscriber(subscription.getUser().getEmail());
		userSubscription.setTransactionId(uuid);
		userSubscription.setBankName(subscription.getBankName());
		userSubscription.setTellerNumber(subscription.getTellerNumber());
		userSubscription.setTellerDate(subscription.getTellerDate());
		userSubscription.setAmountPaid(subscription.getAmountPaid());
		userSubscription.setDateCreated(new DateTime());
		//
		Subscription subscribedItem = subscriptionRepository.findOne(subscription.getSubscription().getId());
		Set<UserSubscription> subcribedItemCollection = subscribedItem.getUserSubscriptions();
		subcribedItemCollection.add(userSubscription);
		//
		subscriptionRepository.save(subscribedItem);
	}
*/
	@Override
	public Subscription findByItemName(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*public Vendor getSelectedVendor(Long aVendorId) {
        System.out.println("getSelectedVendor() Method just called");
        List<Vendor> vendors = getVendors();
        Iterator vendorIter = vendors.iterator();
        while (vendorIter.hasNext()) {
            Vendor vendor = (Vendor) vendorIter.next();
            if (aVendorId.equals(vendor.getId())) {
                System.out.println("A new Product will be added to stock in :" + vendor.getVendorName());
                return vendor;
            }
        }
        System.out.println("Category not found.............................. ");
        return new Vendor(new Long(0), "name", "emailAddress");
    }*/
}
