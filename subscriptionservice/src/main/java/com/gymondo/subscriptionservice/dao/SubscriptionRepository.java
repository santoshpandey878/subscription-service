package com.gymondo.subscriptionservice.dao;

import java.util.List;

import com.gymondo.subscriptionservice.entity.Subscription;

/**
 * Repository to handle all database operation for subscription
 */
public interface SubscriptionRepository extends BaseRepository<Subscription, Long>{

	/**
	 * Method to get all subscriptions of a user by email
	 * @param email
	 * @return
	 */
	List<Subscription> findByUser_Email(String email);

}