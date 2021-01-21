package com.gymondo.subscriptionservice.service;

import java.util.List;

import com.gymondo.subscriptionservice.core.constant.SubscriptionStatus;
import com.gymondo.subscriptionservice.entity.Subscription;

public interface SubscriptionService {

	/**
	 * Method to get subscription by id
	 * @param subscriptionId
	 * @return
	 */
	Subscription getSubscriptionById(Long subscriptionId);

	/**
	 * Method to get all subscription of a user
	 * @param userEmail
	 * @return
	 */
	List<Subscription> getAllSubscriptionsByUser(String userEmail);

	/**
	 * Method to create subscription for a product by user
	 * @param subscription
	 * @param productId
	 * @param userEmail
	 * @return
	 */
	Subscription createSubscription(Subscription subscription, Long productId, String userEmail);

	/**
	 * Method to pause/unpause/cancel a subscription of user
	 * @param subscriptionId
	 * @param status
	 * @return
	 */
	Subscription changeSubscriptionStatus(Long subscriptionId, SubscriptionStatus status);


}
