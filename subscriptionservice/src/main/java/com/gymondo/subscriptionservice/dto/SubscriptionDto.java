package com.gymondo.subscriptionservice.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.gymondo.subscriptionservice.core.constant.SubscriptionStatus;
import com.gymondo.subscriptionservice.core.constant.SubscriptionType;

import io.swagger.annotations.ApiModel;

@ApiModel(description="All details about subscription.")
public class SubscriptionDto {

	private Long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String duration;
	private SubscriptionType subscriptionType;
	private SubscriptionStatus subscriptionStatus;
	private double basePrice;
	private double discountAmount;
	private double taxAmount;
	private double totalPrice;
	private Long productId;
	private String userEmail;
	private Long voucherId;
	private boolean trialActive;

	public SubscriptionDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public SubscriptionStatus getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@NotBlank(message = "Product id cannot be empty")
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@NotBlank(message = "User email cannot be empty")
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@NotBlank(message = "Subscription Type cannot be empty")
	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public Long getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}

	public boolean isTrialActive() {
		return trialActive;
	}

	public void setTrialActive(boolean trialActive) {
		this.trialActive = trialActive;
	}

}
