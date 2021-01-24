package com.gymondo.subscriptionservice.dao;

import java.util.List;

import com.gymondo.subscriptionservice.entity.Product;

/**
 * Repository to handle all database operation for Product
 */
public interface ProductRepository extends BaseRepository<Product, Long>{

	/**
	 * Method to get all products by voucher id
	 * @param voucherId
	 * @return
	 */
	List<Product> findByVouchers_Id(Long voucherId);
}