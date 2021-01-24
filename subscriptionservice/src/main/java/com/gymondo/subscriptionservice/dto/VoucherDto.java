package com.gymondo.subscriptionservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.gymondo.subscriptionservice.core.constant.VoucherType;

import io.swagger.annotations.ApiModel;

@ApiModel(description="All details about voucher.")
public class VoucherDto {

	private Long id;
	private String name;
	private VoucherType voucherType;
	private boolean active;
	private double fixedAmount;
	private int percentage;

	public VoucherDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank(message = "Voucher name cannot be empty")
	@Size(max = 100, message = "Voucher name cannot be greater than 100 charecters")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VoucherType getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(VoucherType voucherType) {
		this.voucherType = voucherType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getFixedAmount() {
		return fixedAmount;
	}

	public void setFixedAmount(double fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

}
