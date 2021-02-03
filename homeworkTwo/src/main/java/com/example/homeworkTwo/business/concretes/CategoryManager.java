package com.example.homeworkTwo.business.concretes;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.homeworkTwo.business.abstracts.ICategoryService;
import com.example.homeworkTwo.dataaccess.abstracts.CategoryRepository;
import com.example.homeworkTwo.entities.concretes.Category;

@Service
public class CategoryManager implements ICategoryService {
	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Override
	public List<Category> getAll() 
	{
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> getById(int id) 
	{
		return categoryRepository.findById(id);
	}

	@Override
	public Category add(Category category) 
	{
		return categoryRepository.save(category);
	}
	
	@Override
	public void delete(Category category) 
	{
		categoryRepository.delete(category);
	}

}
