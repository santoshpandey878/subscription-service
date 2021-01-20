package com.gymondo.subscriptionservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gymondo.subscriptionservice.core.utils.DtoConverter;
import com.gymondo.subscriptionservice.dto.SubscriptionDto;
import com.gymondo.subscriptionservice.entity.Subscription;
import com.gymondo.subscriptionservice.service.SubscriptionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *Subscription Controller responsible to handle client requests to create and list subscription
 * DTO is used to interact with client.
 */
@RestController
@RequestMapping("subscriptions")
@Api(value = "Subscription Controller responsible to handle client requests to create and list subscription.")
public class SubscriptionController {

	private final SubscriptionService subscriptionService;
	private final DtoConverter dtoConverter;

	@Autowired
	public SubscriptionController(SubscriptionService subscriptionService,
			DtoConverter dtoConverter) {
		this.subscriptionService = subscriptionService;
		this.dtoConverter = dtoConverter;
	}

	/**
	 * API to list all subscriptions of a user
	 * @param userEmail
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "API to list all subscriptions of a user")
	public List<SubscriptionDto> getAllSubscriptionsByUser(@RequestParam("userEmail") String userEmail) {
		List<Subscription> subscriptions = subscriptionService.getAllSubscriptionsByUser(userEmail);
		return subscriptions.stream()
				.map(subscription -> dtoConverter.convert(subscription, SubscriptionDto.class))
				.collect(Collectors.toList());
	}

	/**
	 * API to create subscription for a product by user
	 * @param subscriptionDto
	 * @return
	 */
	@PostMapping
	@ApiOperation(value = "API to create subscription for a product by user")
	public SubscriptionDto createSubscription(@RequestBody SubscriptionDto subscriptionDto) {
		Subscription subscription = subscriptionService.createSubscription(dtoConverter.convert(subscriptionDto, Subscription.class),
				subscriptionDto.getProductId(), subscriptionDto.getUserEmail());
		return dtoConverter.convert(subscription, SubscriptionDto.class);
	}

}