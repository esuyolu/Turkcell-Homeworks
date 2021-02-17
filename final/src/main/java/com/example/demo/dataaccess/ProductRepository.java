package com.example.demo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.concretes.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
