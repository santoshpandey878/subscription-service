package com.gymondo.subscriptionservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gymondo.subscriptionservice.core.utils.DtoConverter;
import com.gymondo.subscriptionservice.dto.SubscriptionPlanDto;
import com.gymondo.subscriptionservice.service.SubscriptionPlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *SubscriptionPlan Controller responsible to handle client requests to get the subscription plan for a product
 * DTO is used to interact with client.
 */
@RestController
@RequestMapping("subscriptionplan")
@Api(value = "SubscriptionPlan Controller responsible to handle client requests to get the subscription plan for a product.")
public class SubscriptionPlanController {

	private final SubscriptionPlanService subscriptionPlanService;
	private final DtoConverter dtoConverter;

	@Autowired
	public SubscriptionPlanController(SubscriptionPlanService subscriptionPlanService,
			DtoConverter dtoConverter) {
		this.subscriptionPlanService = subscriptionPlanService;
		this.dtoConverter = dtoConverter;
	}

	/**
	 * API to get subscription plan for a product
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "API to get subscription plan for a product")
	public SubscriptionPlanDto getSubscriptionPlanByProductId(@RequestParam("productId") Long productId) {
		return dtoConverter.convert(subscriptionPlanService.getSubscriptionPlanByProductId(productId), SubscriptionPlanDto.class);
	}

}