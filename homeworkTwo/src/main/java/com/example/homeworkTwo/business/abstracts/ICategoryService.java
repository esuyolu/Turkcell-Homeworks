package com.example.homeworkTwo.business.abstracts;

import java.util.List;
import java.util.Optional;
import com.example.homeworkTwo.entities.concretes.Category;

public interface ICategoryService {
	List<Category> getAll();
	Optional<Category> getById(int id);
	Category add(Category category);
	void delete(Category category);
}
