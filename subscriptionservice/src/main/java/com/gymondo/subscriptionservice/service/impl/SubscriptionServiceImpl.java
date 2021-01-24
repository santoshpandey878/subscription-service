package com.gymondo.subscriptionservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gymondo.subscriptionservice.core.constant.ApplicationConstant;
import com.gymondo.subscriptionservice.core.constant.MessageConstant;
import com.gymondo.subscriptionservice.core.constant.SubscriptionStatus;
import com.gymondo.subscriptionservice.core.constant.VoucherType;
import com.gymondo.subscriptionservice.core.exception.ResourceNotFoundException;
import com.gymondo.subscriptionservice.core.exception.ServiceException;
import com.gymondo.subscriptionservice.core.utils.MessageUtil;
import com.gymondo.subscriptionservice.core.utils.NullUtil;
import com.gymondo.subscriptionservice.dao.SubscriptionRepository;
import com.gymondo.subscriptionservice.entity.Product;
import com.gymondo.subscriptionservice.entity.Subscription;
import com.gymondo.subscriptionservice.entity.SubscriptionPlan;
import com.gymondo.subscriptionservice.entity.User;
import com.gymondo.subscriptionservice.entity.Voucher;
import com.gymondo.subscriptionservice.service.ProductService;
import com.gymondo.subscriptionservice.service.SubscriptionService;
import com.gymondo.subscriptionservice.service.UserService;
import com.gymondo.subscriptionservice.service.VoucherService;

/**
 * Service class responsible to have all business logic for a product subscription by user
 * @author santosh
 *
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;
	private final UserService userService;
	private final ProductService productService;
	private final VoucherService voucherService;
	private final MessageUtil message;

	@Autowired
	public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
			UserService userService,
			ProductService productService,
			VoucherService voucherService,
			MessageUtil message) {
		this.subscriptionRepository = subscriptionRepository;
		this.userService = userService;
		this.productService = productService;
		this.voucherService = voucherService;
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
	public Subscription createSubscription(Subscription subscription, Long productId, String userEmail, Long voucherId) {
		User user = userService.getUserByEmail(userEmail);
		Product product = productService.getProductById(productId);
		SubscriptionPlan subscriptionPlan = product.getSubscriptionPlan();
		//validate user subscription for selected product
		validateUserSubscription(productId, userEmail);

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
		// add trial of one month
		addTrial(subscription, newSubscription);
		// get discount
		double discount = getDiscount(newSubscription, productId, voucherId);
		// get tax
		double taxAmount = (newSubscription.getBasePrice()*ApplicationConstant.TAX_PERCENTAGE)/100;
		newSubscription.setTaxAmount(taxAmount);
		newSubscription.setDiscountAmount(discount);
		newSubscription.setTotalPrice(newSubscription.getBasePrice() + taxAmount - discount);
		newSubscription.setSubscriptionPlan(subscriptionPlan);
		newSubscription.setUser(user);
		newSubscription.setProduct(product);

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
	 * Method to add trial of one month
	 * @param subscription
	 * @param newSubscription
	 */
	private void addTrial(Subscription subscription, Subscription newSubscription) {
		if(subscription.isTrialActive()) {
			newSubscription.setTrialStartDate(LocalDateTime.now());
			newSubscription.setTrialEndDate(LocalDateTime.now().plusDays(30));
			newSubscription.setStartDate(newSubscription.getStartDate().plusDays(30));
			newSubscription.setEndDate(newSubscription.getEndDate().plusDays(30));
		}
	}

	/**
	 * Method to validate user's subscription for particular product
	 * @param productId
	 * @param userEmail
	 */
	private void validateUserSubscription(Long productId, String userEmail) {
		if(NullUtil.isNotNull(subscriptionRepository.findByUser_EmailAndProduct_IdAndSubscriptionStatusNot(userEmail, productId, SubscriptionStatus.CANCEL))) {
			throw new ServiceException(message.get(MessageConstant.USER_ALREADY_SUBSCRIBED));
		}
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
				throw new ServiceException(message.get(MessageConstant.SUBSCRIPTION_ALREADY_CANCELLED));
			}
			break;
		case PAUSE:
			if(subscription.getSubscriptionStatus() != SubscriptionStatus.ACTIVE) {
				throw new ServiceException(message.get(MessageConstant.SUBSCRIPTION_IS_NOT_ACTIVE));
			}
			if(subscription.isTrialActive()) {
				throw new ServiceException(message.get(MessageConstant.SUBSCRIPTION_NOT_PAUSED_IN_TRIAL));
			}
			break;
		case ACTIVE:
			if(subscription.getSubscriptionStatus() == SubscriptionStatus.ACTIVE) {
				throw new ServiceException(message.get(MessageConstant.SUBSCRIPTION_ALREADY_ACTIVE));
			} else if(subscription.getSubscriptionStatus() == SubscriptionStatus.CANCEL) {
				throw new ServiceException(message.get(MessageConstant.SUBSCRIPTION_CANCELLED));
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Method to get discount from voucher for a product
	 * @param subscription
	 * @param productId
	 * @param voucherId
	 * @return
	 */
	private double getDiscount(Subscription subscription, Long productId, Long voucherId) {
		double discount = 0;
		if(null != voucherId) {
			Voucher voucher = voucherService.getVoucherById(voucherId);
			List<Long> productIds = voucher.getProducts().stream().map(product -> product.getId()).collect(Collectors.toList());
			if(productIds.contains(productId)) {
				if(voucher.getVoucherType() == VoucherType.FIXED_AMOUNT) {
					discount = voucher.getFixedAmount();
				} else if(voucher.getVoucherType() == VoucherType.PERCENTAGE) {
					discount = (subscription.getBasePrice()*voucher.getPercentage())/100;
				}
			} else {
				throw new ServiceException(message.get(MessageConstant.VOUCHER_NOT_APPLICABLE));
			}
		}
		return discount;
	}

}
