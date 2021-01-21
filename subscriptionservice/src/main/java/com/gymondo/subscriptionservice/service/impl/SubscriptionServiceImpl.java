package com.gymondo.subscriptionservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gymondo.subscriptionservice.core.constant.ApplicationConstant;
import com.gymondo.subscriptionservice.core.constant.MessageConstant;
import com.gymondo.subscriptionservice.core.constant.SubscriptionStatus;
import com.gymondo.subscriptionservice.core.exception.ResourceNotFoundException;
import com.gymondo.subscriptionservice.core.exception.ServiceException;
import com.gymondo.subscriptionservice.core.utils.MessageUtil;
import com.gymondo.subscriptionservice.dao.SubscriptionRepository;
import com.gymondo.subscriptionservice.entity.Product;
import com.gymondo.subscriptionservice.entity.Subscription;
import com.gymondo.subscriptionservice.entity.SubscriptionPlan;
import com.gymondo.subscriptionservice.entity.User;
import com.gymondo.subscriptionservice.service.ProductService;
import com.gymondo.subscriptionservice.service.SubscriptionService;
import com.gymondo.subscriptionservice.service.UserService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;
	private final UserService userService;
	private final ProductService productService;
	private final MessageUtil message;

	@Autowired
	public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
			UserService userService,
			ProductService productService,
			MessageUtil message) {
		this.subscriptionRepository = subscriptionRepository;
		this.userService = userService;
		this.productService = productService;
		this.message = message;
	}

	@Override
	public Subscription getSubscriptionById(Long subscriptionId) {
		return subscriptionRepository.findById(subscriptionId)
				.orElseThrow(() -> new ResourceNotFoundException(message.get(MessageConstant.SUBSCRIPTION_NOT_FOUND, subscriptionId)));
	}

	@Override
	public List<Subscription> getAllSubscriptionsByUser(String userEmail) {
		return subscriptionRepository.findByUser_Email(userEmail);
	}

	@Transactional
	@Override
	public Subscription createSubscription(Subscription subscription, Long productId, String userEmail) {
		User user = userService.getUserByEmail(userEmail);
		Product product = productService.getProductById(productId);
		SubscriptionPlan subscriptionPlan = product.getSubscriptionPlan();

		Subscription newSubscription = new Subscription();
		newSubscription.setStartDate(LocalDateTime.now());
		newSubscription.setSubscriptionStatus(SubscriptionStatus.ACTIVE);
		newSubscription.setSubscriptionType(subscription.getSubscriptionType());

		switch(subscription.getSubscriptionType()) {
		case MONTHLY:
			newSubscription.setEndDate(LocalDateTime.now().plusDays(30));
			newSubscription.setBasePrice(subscriptionPlan.getPricePerMonth());
			break;
		case QUARTERLY:
			newSubscription.setEndDate(LocalDateTime.now().plusDays(90));
			newSubscription.setBasePrice(subscriptionPlan.getPricePerMonth()*3);
			break;
		case HALF_YEARLY:
			newSubscription.setEndDate(LocalDateTime.now().plusDays(180));
			newSubscription.setBasePrice(subscriptionPlan.getPricePerMonth()*6);
			break;
		case YEARLY:
			newSubscription.setEndDate(LocalDateTime.now().plusDays(365));
			newSubscription.setBasePrice(subscriptionPlan.getPricePerMonth()*12);
			break;
		default:
			break;
		}

		double taxAmount = (newSubscription.getBasePrice()*ApplicationConstant.TAX_PERCENTAGE)/100;
		newSubscription.setTaxAmount(taxAmount);
		newSubscription.setTotalPrice(newSubscription.getBasePrice() + taxAmount - 0);
		newSubscription.setSubscriptionPlan(subscriptionPlan);
		newSubscription.setUser(user);

		return subscriptionRepository.save(newSubscription);
	}

	@Transactional
	@Override
	public Subscription changeSubscriptionStatus(Long subscriptionId, SubscriptionStatus status) {
		Subscription subscription = getSubscriptionById(subscriptionId);
		validateSubscriptionStatus(subscription, status);
		subscription.setSubscriptionStatus(status);
		return subscriptionRepository.save(subscription);
	}

	/**
	 * Method to validate subscription status
	 * @param subscription
	 * @param status
	 */
	private void validateSubscriptionStatus(Subscription subscription, SubscriptionStatus status) {
		switch (status) {
		case CANCEL:
			if(subscription.getSubscriptionStatus() == SubscriptionStatus.CANCEL) {
				throw new ServiceException(message.get(ApplicationConstant.SUBSCRIPTION_ALREADY_CANCELLED));
			}
			break;
		case PAUSE:
			if(subscription.getSubscriptionStatus() != SubscriptionStatus.ACTIVE) {
				throw new ServiceException(message.get(ApplicationConstant.SUBSCRIPTION_IS_NOT_ACTIVE));
			}
			break;
		case ACTIVE:
			if(subscription.getSubscriptionStatus() == SubscriptionStatus.ACTIVE) {
				throw new ServiceException(message.get(ApplicationConstant.SUBSCRIPTION_ALREADY_ACTIVE));
			} else if(subscription.getSubscriptionStatus() == SubscriptionStatus.CANCEL) {
				throw new ServiceException(message.get(ApplicationConstant.SUBSCRIPTION_CANCELLED));
			}
			break;
		default:
			break;
		}
	}

}
