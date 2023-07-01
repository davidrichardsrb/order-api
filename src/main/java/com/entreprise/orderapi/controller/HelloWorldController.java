package com.entreprise.orderapi.controller;


import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entreprise.orderapi.entity.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController //Bean y ponerlo en el contexto de Spring
public class HelloWorldController {
	
//	private static final Logger log  = Logger.getLogger(HelloWorldController.class.getCanonicalName());
	
	@GetMapping(value = "hello")
	public String hello() {
		return "Hello World Changed";
	}
	
	
	@GetMapping(value = "product")
	public Product findProduct() {
		
		log.info("FindProduct");
		/*
		Product product = new Product();
		product.setId(1L);
		product.setName("Candy");
		return product;*/
		
		Product product2 = Product.builder()
				.id(1L)
				.name("Product 1")
				.build();
		return product2;
				
	}

}
