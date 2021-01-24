package com.gymondo.subscriptionservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.gymondo.subscriptionservice.core.constant.SubscriptionStatus;
import com.gymondo.subscriptionservice.core.constant.SubscriptionType;
import com.gymondo.subscriptionservice.core.utils.DateUtilities;
import com.gymondo.subscriptionservice.core.utils.NullUtil;
import com.gymondo.subscriptionservice.core.utils.PostgreSQLEnumType;

@Entity
@Table(name = "subscription")
@TypeDef(name = "enumType",typeClass = PostgreSQLEnumType.class)
public class Subscription {

	private Long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String duration;
	private SubscriptionType subscriptionType;
	private SubscriptionStatus subscriptionStatus;
	private double basePrice;
	private double taxAmount;
	private double discountAmount;
	private double totalPrice;
	private SubscriptionPlan subscriptionPlan;
	private User user;
	private Product product;
	private boolean trialActive;
	private LocalDateTime trialStartDate;
	private LocalDateTime trialEndDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "start_date", nullable = false)
	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", nullable = false)
	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	@Enumerated(EnumType.STRING)
	@Type(type = "enumType")
	@Column(name = "subscription_status")
	public SubscriptionStatus getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	@Column(name = "base_price", nullable = false)
	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	@Column(name = "tax_amount", nullable = false)
	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	@Column(name = "total_price")
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "discount_amount")
	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	@ManyToOne
	@JoinColumn(name="subscription_plan_id")
	public SubscriptionPlan getSubscriptionPlan() {
		return subscriptionPlan;
	}

	public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Transient
	public String getDuration() {
		LocalDateTime endTime = null != endDate ? endDate : LocalDateTime.now(); 
		duration = DateUtilities.getDuration(endTime, startDate);
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Enumerated(EnumType.STRING)
	@Type(type = "enumType")
	@Column(name = "subscription_type")
	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Transient
	public boolean isTrialActive() {
		if(!trialActive) {
			if(NullUtil.isNotNull(trialStartDate) 
					&& NullUtil.isNotNull(trialEndDate) 
					&& LocalDateTime.now().isBefore(trialEndDate)) {
				trialActive = true;
			} else {
				trialActive = false;
			}
		}
		return trialActive;
	}

	public void setTrialActive(boolean trialActive) {
		this.trialActive = trialActive;
	}

	@Column(name = "trial_start_date", nullable = false)
	public LocalDateTime getTrialStartDate() {
		return trialStartDate;
	}

	public void setTrialStartDate(LocalDateTime trialStartDate) {
		this.trialStartDate = trialStartDate;
	}

	@Column(name = "trial_end_date", nullable = false)
	public LocalDateTime getTrialEndDate() {
		return trialEndDate;
	}

	public void setTrialEndDate(LocalDateTime trialEndDate) {
		this.trialEndDate = trialEndDate;
	}

}
