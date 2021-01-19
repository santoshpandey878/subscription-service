package com.gymondo.subscriptionservice.dto;

import com.gymondo.subscriptionservice.core.constant.SubscriptionType;

public class SubscriptionPlanDto {

	private Long id;
	private double pricePerMonth;
	private SubscriptionType subscriptionType;
	private double priceBeforeDiscount;
	private double discountAmount;
	private double priceAfterDiscount;

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

	public double getPriceBeforeDiscount() {
		switch(this.subscriptionType) {
		case MONTHLY:
			priceBeforeDiscount = pricePerMonth;
			break;
		case QUARTERLY:
			priceBeforeDiscount = pricePerMonth*3;
			break;
		case HALF_YEARLY:
			priceBeforeDiscount = pricePerMonth*6;
			break;
		case YEARLY:
			priceBeforeDiscount = pricePerMonth*12;
			break;
		}
		return priceBeforeDiscount;
	}

	public void setPriceBeforeDiscount(double priceBeforeDiscount) {
		this.priceBeforeDiscount = priceBeforeDiscount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getPriceAfterDiscount() {
		priceAfterDiscount = priceBeforeDiscount - discountAmount;
		return priceAfterDiscount;
	}

	public void setPriceAfterDiscount(double priceAfterDiscount) {
		this.priceAfterDiscount = priceAfterDiscount;
	}

}
