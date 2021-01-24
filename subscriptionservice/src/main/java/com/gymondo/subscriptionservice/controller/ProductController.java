package com.gymondo.subscriptionservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymondo.subscriptionservice.core.utils.DtoConverter;
import com.gymondo.subscriptionservice.dto.ProductDto;
import com.gymondo.subscriptionservice.entity.Product;
import com.gymondo.subscriptionservice.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *Product Controller responsible to handle client requests to list product details
 * DTO is used to interact with client.
 */
@RestController
@RequestMapping("products")
@Api(value = "Product Controller responsible to handle client requests to list product details.")
public class ProductController {

	private final ProductService productService;
	private final DtoConverter dtoConverter;

	@Autowired
	public ProductController(ProductService productService,
			DtoConverter dtoConverter) {
		this.productService = productService;
		this.dtoConverter = dtoConverter;
	}

	/**
	 * API to list all products
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "API to find all products")
	public List<ProductDto> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return products.stream()
				.map(product -> dtoConverter.convert(product, ProductDto.class))
				.collect(Collectors.toList());
	}

	/**
	 * API to get a product by product id
	 * @param productId
	 * @return
	 */
	@GetMapping("{productId}")
	@ApiOperation(value = "API to get a product by product id")
	public ProductDto getProductById(@PathVariable Long productId) {
		return dtoConverter.convert(productService.getProductById(productId), ProductDto.class);
	}

	/**
	 * API to get all products for a voucher
	 * @param voucherId
	 * @return
	 */
	@GetMapping("vouchers/{voucherId}")
	@ApiOperation(value = "API to get all products for a voucher")
	public List<ProductDto> getAllProductsByVoucherId(@PathVariable Long voucherId) {
		List<Product> products = productService.getAllProductsByVoucherId(voucherId);
		return products.stream()
				.map(product -> dtoConverter.convert(product, ProductDto.class))
				.collect(Collectors.toList());
	}

}