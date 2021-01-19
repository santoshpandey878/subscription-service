package com.gymondo.subscriptionservice.dao;

import com.gymondo.subscriptionservice.entity.SubscriptionPlan;

/**
 * Repository to handle all database operation for subscription plan
 */
public interface SubscriptionPlanRepository extends BaseRepository<SubscriptionPlan, Long>{

	/**
	 * Method to get subscription plan by product id
	 * @param productId
	 * @return
	 */
	SubscriptionPlan findByProduct_Id(Long productId);


}