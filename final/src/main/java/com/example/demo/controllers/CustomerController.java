package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dataaccess.CustomerRepository;
import com.example.demo.entities.concretes.Customer;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("customers")
	public ResponseEntity<List<Customer>> getCustomers()
	{
		List<Customer> customers = customerRepository.findAll();
		
		if (customers.isEmpty())
			throw new RuntimeException("no customer was found");
		
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@GetMapping("customers/{customerId}")
	public ResponseEntity<Customer> readCustomer(@PathVariable(value="customerId") String id)
	{
		Customer customer = customerRepository.findById(id);
		
		if (customer == null)
			throw new RuntimeException("no customer was found");
		
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@PostMapping("customers")
	public ResponseEntity<Customer> add(@RequestBody Customer customer)
	{
		if (customerRepository.existsById(customer.getId()))
			return new ResponseEntity<Customer>(customer, HttpStatus.FOUND);
		
		Customer item = customerRepository.save(customer);
		
		return new ResponseEntity<Customer>(item, HttpStatus.CREATED);
	}
	
	@PutMapping("customers/{customerId}")
	public ResponseEntity<Customer> update(@RequestBody Customer customer, @PathVariable(value="customerId") String customerId)
	{
		Customer item = customerRepository.findById(customerId);
		
		if (item == null)
			throw new RuntimeException("no customer was found");
		
		Customer updated = customerRepository.save(customer);
		
		return new ResponseEntity<Customer>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("customers/{customerId}")
	public ResponseEntity<Customer> delete(@PathVariable(value="customerId") String id)
	{
		Customer customer = customerRepository.findById(id);
		
		if (customer == null)
			throw new RuntimeException("no customer was found");
		
		customerRepository.delete(customer);
		
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
}
