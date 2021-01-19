package com.gymondo.subscriptionservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymondo.subscriptionservice.dao.SubscriptionPlanRepository;
import com.gymondo.subscriptionservice.entity.SubscriptionPlan;
import com.gymondo.subscriptionservice.service.SubscriptionPlanService;

@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {
	
	@Autowired private SubscriptionPlanRepository subscriptionPlanRepository;

	@Override
	public SubscriptionPlan getSubscriptionPlanByProductId(Long productId) {
		return subscriptionPlanRepository.findByProduct_Id(productId);
	}

}
