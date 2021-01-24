package com.gymondo.subscriptionservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymondo.subscriptionservice.core.utils.DtoConverter;
import com.gymondo.subscriptionservice.dto.VoucherDto;
import com.gymondo.subscriptionservice.entity.Voucher;
import com.gymondo.subscriptionservice.service.VoucherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *Voucher Controller responsible to handle client requests to list vouchers and all products for a voucher
 * DTO is used to interact with client.
 */
@RestController
@RequestMapping("vouchers")
@Api(value = "Voucher Controller responsible to handle client requests to list vouchers and all products for a voucher.")
public class VoucherController {

	private final VoucherService voucherService;
	private final DtoConverter dtoConverter;

	@Autowired
	public VoucherController(VoucherService voucherService,
			DtoConverter dtoConverter) {
		this.voucherService = voucherService;
		this.dtoConverter = dtoConverter;
	}

	/**
	 * API to list all vouchers
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "API to find all vouchers")
	public List<VoucherDto> getAllVouchers() {
		List<Voucher> vouchers = voucherService.getAllVouchers();
		return vouchers.stream()
				.map(voucher -> dtoConverter.convert(voucher, VoucherDto.class))
				.collect(Collectors.toList());
	}

}