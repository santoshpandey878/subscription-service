package com.gymondo.subscriptionservice.service;

import com.gymondo.subscriptionservice.entity.SubscriptionPlan;

public interface SubscriptionPlanService {

	/**
	 * Method to get subscription plan by product id
	 * @param productId
	 * @return
	 */
	SubscriptionPlan getSubscriptionPlanByProductId(Long productId);

}
