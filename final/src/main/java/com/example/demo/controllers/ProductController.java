package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dataaccess.ProductRepository;
import com.example.demo.entities.concretes.Product;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
    private  ProductRepository productRepository;
	
	@GetMapping("products")
	public ResponseEntity<List<Product>> getProducts()
	{
		List<Product> products = productRepository.findAll();
		
		if (products.isEmpty())
			throw new RuntimeException("no product was found");
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("products/{productId}")
	public ResponseEntity<Product> readProduct(@PathVariable(value="productId") int id)
	{
		Optional<Product> product = productRepository.findById(id);
		
		if (product.isEmpty())
			throw new RuntimeException("no product was found");
		
		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
	}
	
	@PostMapping("products")
	public ResponseEntity<Product> add(@RequestBody Product product)
	{
		Product result = productRepository.save(product);
		
		return new ResponseEntity<Product>(result, HttpStatus.CREATED);
	}
	
	@PutMapping("products/{productId}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable(value="productId") int id)
	{
		Optional<Product> productToUpdate = productRepository.findById(id);
		
		if (productToUpdate.isEmpty())
			throw new RuntimeException("no product was found");
		
		Product updated = productRepository.save(product);
		
		return new ResponseEntity<Product>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("products/{productId}")
	public ResponseEntity<?> delete(@PathVariable(value="productId") int id)
	{
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return ResponseEntity.ok("Product " + id + " deleted.");
		}
		else {
			throw new RuntimeException("no product was found");
		}
	}
}
