package com.example.turkcell.business.abstracts;

import java.util.List;
import java.util.Optional;
import com.example.turkcell.entities.concretes.Credit;
import com.example.turkcell.entities.concretes.Customer;

public interface ICustomerService {
	List<Customer> findAll();
	Optional<Customer> findById(int id);
	Customer save(Customer customer);
	void delete(Customer customer);
	List<Credit> creditApplication(int customerId);
}
