package com.gymondo.subscriptionservice.service;

import java.util.List;

import com.gymondo.subscriptionservice.entity.Voucher;

public interface VoucherService {

	/**
	 * Method to get voucher by id
	 * @param voucherId
	 * @return
	 */
	Voucher getVoucherById(Long voucherId);

	/**
	 * Method to get all vouchers
	 * @return
	 */
	List<Voucher> getAllVouchers();

}
