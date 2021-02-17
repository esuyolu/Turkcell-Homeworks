package com.example.demo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.concretes.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public Customer findByContactName(String contactName);
	public Customer findById(String id);
	public boolean existsById(String id);
}
