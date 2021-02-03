package com.example.homeworkTwo.dataaccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.homeworkTwo.entities.concretes.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
