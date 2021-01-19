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

}
