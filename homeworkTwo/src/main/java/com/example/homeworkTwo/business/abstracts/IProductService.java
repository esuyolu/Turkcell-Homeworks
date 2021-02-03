package com.example.homeworkTwo.business.abstracts;

import java.util.List;
import java.util.Optional;
import com.example.homeworkTwo.entities.concretes.Product;

public interface IProductService {
	List<Product> getAll();
	Optional<Product> getById(int id);
	Product add(Product product);
	void delete(Product product);
}
