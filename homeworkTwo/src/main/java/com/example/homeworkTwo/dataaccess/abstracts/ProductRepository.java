package com.example.homeworkTwo.dataaccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.homeworkTwo.entities.concretes.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("select count(p.id) from Product p where p.categoryId = :#{#product.categoryId} group by p.categoryId")
	Long countOfProductsByCategory(@Param("product") Product product);
}
