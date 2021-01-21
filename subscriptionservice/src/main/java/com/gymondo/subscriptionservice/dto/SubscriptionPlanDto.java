package com.gymondo.subscriptionservice.dto;

import com.gymondo.subscriptionservice.core.constant.SubscriptionType;

public class SubscriptionPlanDto {

	private Long id;
	private double pricePerMonth;
	private SubscriptionType subscriptionType;
	private double basePrice;
	private double discountAmount;
	private double taxAmount;
	private double totalPrice;

	public SubscriptionPlanDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPricePerMonth() {
		return pricePerMonth;
	}

	public void setPricePerMonth(double pricePerMonth) {
		this.pricePerMonth = pricePerMonth;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getBasePrice() {
		switch(this.subscriptionType) {
		case MONTHLY:
			basePrice = pricePerMonth;
			break;
		case QUARTERLY:
			basePrice = pricePerMonth*3;
			break;
		case HALF_YEARLY:
			basePrice = pricePerMonth*6;
			break;
		case YEARLY:
			basePrice = pricePerMonth*12;
			break;
		}
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
		totalPrice = basePrice + taxAmount - discountAmount;
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
