package com.gymondo.subscriptionservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymondo.subscriptionservice.core.constant.MessageConstant;
import com.gymondo.subscriptionservice.core.exception.ResourceNotFoundException;
import com.gymondo.subscriptionservice.core.utils.MessageUtil;
import com.gymondo.subscriptionservice.dao.VoucherRepository;
import com.gymondo.subscriptionservice.entity.Voucher;
import com.gymondo.subscriptionservice.service.VoucherService;

/**
 * Service class responsible to have all business logic for product Voucher
 * @author santosh
 *
 */
@Service
public class VoucherServiceImpl implements VoucherService {

	private final VoucherRepository voucherRepository;
	private final MessageUtil message;

	@Autowired
	public VoucherServiceImpl(VoucherRepository voucherRepository,
			MessageUtil message) {
		this.voucherRepository = voucherRepository;
		this.message = message;
	}

	@Override
	public List<Voucher> getAllVouchers() {
		return voucherRepository.findAll();
	}

	@Override
	public Voucher getVoucherById(Long voucherId) {
		return voucherRepository.findById(voucherId)
				.orElseThrow(() -> new ResourceNotFoundException(message.get(MessageConstant.VOUCHER_NOT_FOUND, voucherId)));
	}

}
