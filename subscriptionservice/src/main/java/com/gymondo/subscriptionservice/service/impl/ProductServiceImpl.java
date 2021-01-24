package com.gymondo.subscriptionservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymondo.subscriptionservice.core.constant.MessageConstant;
import com.gymondo.subscriptionservice.core.exception.ResourceNotFoundException;
import com.gymondo.subscriptionservice.core.utils.MessageUtil;
import com.gymondo.subscriptionservice.dao.ProductRepository;
import com.gymondo.subscriptionservice.entity.Product;
import com.gymondo.subscriptionservice.service.ProductService;

/**
 * Service class responsible to have all business logic for Product
 * @author santosh
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	private final MessageUtil message;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,
			MessageUtil message) {
		this.productRepository = productRepository;
		this.message = message;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException(message.get(MessageConstant.PRODUCT_NOT_FOUND, productId)));
	}

	@Override
	public List<Product> getAllProductsByVoucherId(Long voucherId) {
		return productRepository.findByVouchers_Id(voucherId);
	}

}
