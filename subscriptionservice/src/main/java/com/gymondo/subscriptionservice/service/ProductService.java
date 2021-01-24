package com.gymondo.subscriptionservice.service;

import java.util.List;

import com.gymondo.subscriptionservice.entity.Product;

public interface ProductService {

	/**
	 * Method to fetch all products
	 * @return
	 */
	List<Product> getAllProducts();

	/**
	 * Method to fetch single project by id
	 * @param productId
	 * @return
	 */
	Product getProductById(Long productId);

	/**
	 * Method to get all products by voucher id
	 * @param voucherId
	 * @return
	 */
	List<Product> getAllProductsByVoucherId(Long voucherId);

}
