package com.gymondo.subscriptionservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gymondo.subscriptionservice.core.constant.SubscriptionStatus;
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
			newSubscription.setPriceBeforeDiscount(subscriptionPlan.getPricePerMonth());
			break;
		case QUARTERLY:
			newSubscription.setEndDate(LocalDateTime.now().plusDays(90));
			newSubscription.setPriceBeforeDiscount(subscriptionPlan.getPricePerMonth()*3);
			break;
		case HALF_YEARLY:
			newSubscription.setEndDate(LocalDateTime.now().plusDays(180));
			newSubscription.setPriceBeforeDiscount(subscriptionPlan.getPricePerMonth()*6);
			break;
		case YEARLY:
			newSubscription.setEndDate(LocalDateTime.now().plusDays(365));
			newSubscription.setPriceBeforeDiscount(subscriptionPlan.getPricePerMonth()*12);
			break;
		default:
			break;
		}

		newSubscription.setPriceAfterDiscount(newSubscription.getPriceBeforeDiscount()-0);
		newSubscription.setSubscriptionPlan(subscriptionPlan);
		newSubscription.setUser(user);

		return subscriptionRepository.save(newSubscription);
	}

}
