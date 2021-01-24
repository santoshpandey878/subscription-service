package com.gymondo.subscriptionservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymondo.subscriptionservice.core.constant.MessageConstant;
import com.gymondo.subscriptionservice.core.exception.ResourceNotFoundException;
import com.gymondo.subscriptionservice.core.utils.MessageUtil;
import com.gymondo.subscriptionservice.core.utils.NullUtil;
import com.gymondo.subscriptionservice.dao.SubscriptionPlanRepository;
import com.gymondo.subscriptionservice.entity.SubscriptionPlan;
import com.gymondo.subscriptionservice.service.SubscriptionPlanService;

/**
 * Service class responsible to have all business logic for Subscription Plan
 * @author santosh
 *
 */
@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

	private final SubscriptionPlanRepository subscriptionPlanRepository;
	private final MessageUtil message;

	@Autowired
	public SubscriptionPlanServiceImpl(SubscriptionPlanRepository subscriptionPlanRepository,
			MessageUtil message) {
		this.subscriptionPlanRepository = subscriptionPlanRepository;
		this.message = message;
	}

	@Override
	public SubscriptionPlan getSubscriptionPlanByProductId(Long productId) {
		SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findByProduct_Id(productId);
		if(NullUtil.isNull(subscriptionPlan)) {
			throw new ResourceNotFoundException(message.get(MessageConstant.SUBSCRIPTION_PLAN_NOT_FOUND, productId));
		}
		return subscriptionPlan;
	}

}
