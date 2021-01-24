package com.gymondo.subscriptionservice.dao;

import java.util.List;

import com.gymondo.subscriptionservice.core.constant.SubscriptionStatus;
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

	/**
	 * Method to find subscription by user's email for specific product
	 * @param email
	 * @param productId
	 * @return
	 */
	Subscription findByUser_EmailAndProduct_IdAndSubscriptionStatusNot(String email, Long productId, SubscriptionStatus status);

}