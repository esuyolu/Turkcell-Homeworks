package com.example.turkcell.controllers;

import java.util.List;
import java.util.Optional;
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
import com.example.turkcell.business.abstracts.ICustomerService;
import com.example.turkcell.entities.concretes.Customer;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	@Autowired
	ICustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers()
	{
		List<Customer> customers = customerService.findAll();
		
		if (customers.isEmpty())
			throw new RuntimeException("sistemde musteri bulunamadi");
		
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@GetMapping("customers/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value="customerId") int id)
	{
		Optional<Customer> customer = customerService.findById(id);
		
		if (customer.isEmpty())
			throw new RuntimeException("sistemde musteri bulunamadi");
		
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
	}
	
	@PostMapping("customers")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer)
	{
		Customer item = customerService.save(customer);
		
		return new ResponseEntity<Customer>(item, HttpStatus.CREATED);
	}

	@PutMapping("customers/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable(value="customerId") int id)
	{
		Optional<Customer> item = customerService.findById(id);
		
		if (item.isEmpty())
			throw new RuntimeException("sistemde musteri bulunamadi");
		
		Customer updated = customerService.save(customer);
		
		return new ResponseEntity<Customer>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("customers/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable(value="customerId") int id)
	{
		Optional<Customer> customer = customerService.findById(id);
		
		if (customer.isEmpty())
			throw new RuntimeException("sistemde musteri bulunamadi");
		
		customerService.delete(customer.get());
		
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
}
