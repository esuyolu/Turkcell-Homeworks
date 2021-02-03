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
import com.example.homeworkTwo.business.abstracts.ICategoryService;
import com.example.homeworkTwo.entities.concretes.Category;

@RestController
@RequestMapping("/api/v1")
public class CategoriesController {
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping("/categories")
	public List<Category> getAll()
	{
		return categoryService.getAll();
	}
	
	@GetMapping("/categories/{category_id}")
	public ResponseEntity<Category> getById(@PathVariable(value="category_id") int id) 
	{
		return categoryService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/categories")
	public Category add(@RequestBody Category category)
	{
		return categoryService.add(category);
	}
	
	@PutMapping("/categories/{category_id}")
	public ResponseEntity<Category> update(@PathVariable(value="category_id") int id, @RequestBody Category category) throws Exception
	{
		Category categoryToUpdate = categoryService.getById(id).orElseThrow(() -> new Exception("no category with id: " + id));
		
		categoryToUpdate.setCategoryName(category.getCategoryName());
		categoryToUpdate.setDescription(category.getDescription());
		
		Category updatedCategory = categoryService.add(categoryToUpdate);
		
		return ResponseEntity.ok(updatedCategory);
	}
	
	@DeleteMapping("/categories/{category_id}")
	public Map<String, Boolean> delete(@PathVariable(value="category_id") int id) throws Exception
	{
		Category categoryToDelete = categoryService.getById(id).orElseThrow(() -> new Exception("no category with id: " + id));
		
		categoryService.delete(categoryToDelete);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("silindi", Boolean.TRUE);
		
		return response;
	}
}
