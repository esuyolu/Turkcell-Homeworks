package com.example.homeworkTwo.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.homeworkTwo.business.abstracts.IProductService;
import com.example.homeworkTwo.entities.concretes.Product;

@RestController
@RequestMapping("/api/v1")
public class ProductsController {
	@Autowired
	IProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAll()
	{
		return productService.getAll();
	}
	
	@GetMapping("/products/{product_id}")
	public ResponseEntity<Product> getById(@PathVariable(value="product_id") int id)
	{
		return productService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping("/products")
	public Product add(@RequestBody Product product)
	{
		return productService.add(product);
	}
	
	@PutMapping("/products/{product_id}")
	public ResponseEntity<Product> update(@PathVariable(value="product_id") int id, @RequestBody Product product) throws Exception
	{
		Product productToUpdate = productService.getById(id).orElseThrow(() -> new Exception("no product with id: " + id));
		
		productToUpdate.setCategoryId(product.getCategoryId());
		productToUpdate.setProductName(product.getProductName());
		productToUpdate.setQuantityPerUnit(product.getQuantityPerUnit());
		productToUpdate.setUnitPrice(product.getUnitPrice());
		
		Product updatedProduct = productService.add(productToUpdate);
		
		return ResponseEntity.ok(updatedProduct);
	}
	
	@DeleteMapping("/products/{product_id}")
	public Map<String, Boolean> delete(@PathVariable(value="product_id") int id) throws Exception
	{
		Product productToDelete = productService.getById(id).orElseThrow(() -> new Exception("no product with id: " + id));
		
		productService.delete(productToDelete);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("silindi", Boolean.TRUE);
		
		return response;
	}
}
