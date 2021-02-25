package com.example.turkcell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.turkcell.entities.concretes.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
}
