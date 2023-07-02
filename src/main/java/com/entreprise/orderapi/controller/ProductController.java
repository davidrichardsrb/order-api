package com.entreprise.orderapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entreprise.orderapi.entity.Product;
import com.entreprise.orderapi.repository.ProductRepository;

@RestController
public class ProductController {
	
	private ArrayList<Product> products = new ArrayList<>();
	
	@Autowired
	private ProductRepository productRepo;
	
	public ProductController() {
		for(int c=0;c<10;c++) {
			products.add(Product.builder()
					.id(c+1L)
					.name("Product"+(c+1L))
					.build());
		}
	}
	
/*	@GetMapping(value = "/products")
	public ArrayList<Product> findAll(){
		return products;
	}
	
	@GetMapping(value = "/products/{productId}")
	public ResponseEntity<Product> findById(@PathVariable("productId") Long productId) {
		/*for(Product product: this.products) {
			if(product.getId().longValue() == productId) {
				return product;
			}
		}
		
		Product product = productRepo.findById(productId).orElseThrow(()-> new RuntimeException("No existe el producto"));
		
		return new ResponseEntity<Product>(product,HttpStatus.OK);
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
	}*/
	
	@GetMapping(value = "products")
	public ResponseEntity<List<Product>> findAll(){
		List<Product> products = productRepo.findAll();
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}
	@GetMapping(value = "products/{productId}")
	public ResponseEntity<Product> findById(@PathVariable("productId") Long productId){
		Product product = productRepo.findById(productId).orElseThrow(()-> new RuntimeException("El Producto no Existe"));
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@PutMapping(value = "products")
	public ResponseEntity<Product> update (@RequestBody Product product){
		Product existProduct = productRepo.findById(product.getId()).orElseThrow(()-> new RuntimeException("No existe ese producto"));
		existProduct.setName(product.getName());
		existProduct.setPrice(product.getPrice());
		
		productRepo.save(existProduct);
		return new ResponseEntity<Product>(existProduct,HttpStatus.OK);
	}
	
	@PostMapping(value = "/products")
	public ResponseEntity<Product> create (@RequestBody Product product){
		Product productNew = productRepo.save(product);
		return new ResponseEntity<Product>(productNew,HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "products/{productId}")
	public ResponseEntity<Product> delete(@PathVariable("productId") Long productId){
		Product product = productRepo.findById(productId).orElseThrow(()-> new RuntimeException("No existe el producto"));
		productRepo.delete(product);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}

}
