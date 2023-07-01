package com.entreprise.orderapi.controller;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entreprise.orderapi.entity.Product;

@RestController
public class ProductController {
	
	private ArrayList<Product> products = new ArrayList<>();
	
	public ProductController() {
		for(int c=0;c<10;c++) {
			products.add(Product.builder()
					.id(c+1L)
					.name("Product"+(c+1L))
					.build());
		}
	}
	
	@GetMapping(value = "/products")
	public ArrayList<Product> findAll(){
		return products;
	}
	
	@GetMapping(value = "/products/{productId}")
	public Product findById(@PathVariable("productId") Long productId) {
		for(Product product: this.products) {
			if(product.getId().longValue() == productId) {
				return product;
			}
		}
		return null;
	}
	
	@PostMapping(value = "/products")
	public Product create (@RequestBody Product product) {
		this.products.add(product);
		return product;
	}
	
	@PutMapping(value = "/products")
	public Product update(@RequestBody Product product) {
		for(Product prod: this.products) {
			if(prod.getId().longValue()==product.getId().longValue()) {
				prod.setName(product.getName());
				return prod;
			}
		}
		throw new RuntimeException("No existe el producto");
	}
	
	@DeleteMapping(value = "/products/{productId}")
	public void delete (@PathVariable("productId") Long product) {
		Product deleteProduct = null;
		for(Product prod:this.products) {
			if(prod.getId().longValue()==product.longValue()) {
				deleteProduct = prod;
				break;
			}
		}
		if(deleteProduct == null)throw new RuntimeException("No existe el producto a eliminar");
		
		this.products.remove(deleteProduct);
	}

}
