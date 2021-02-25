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
import com.example.turkcell.business.abstracts.ICreditService;
import com.example.turkcell.business.abstracts.ICustomerService;
import com.example.turkcell.entities.concretes.Credit;

@RestController
@RequestMapping("/api/v1")
public class CreditController {
	@Autowired
	ICreditService creditService;
	
	@Autowired
	ICustomerService customerService;
	
	@GetMapping("/credits")
	public ResponseEntity<List<Credit>> getCredits()
	{
		List<Credit> credits = creditService.findAll();
		
		if (credits.isEmpty())
			throw new RuntimeException("sistemde kredi bulunamadi");
		
		return new ResponseEntity<List<Credit>>(credits, HttpStatus.OK);
	}
	
	@GetMapping("credits/{creditId}")
	public ResponseEntity<Credit> getCreditById(@PathVariable(value="creditId") int id)
	{
		Optional<Credit> credit = creditService.findById(id);
		
		if (credit.isEmpty())
			throw new RuntimeException("sistemde kredi bulunamadi");
		
		return new ResponseEntity<Credit>(credit.get(), HttpStatus.OK);
	}
	
	@GetMapping("credits/customer/{customerId}")
	public ResponseEntity<List<Credit>> getCreditsByCustomerId(@PathVariable(value="customerId") int customerId)
	{
		List<Credit> credits = creditService.findCreditsByCustomerId(customerId);
		
		if (credits.isEmpty())
			throw new RuntimeException("sistemde kredi bulunamadi");
		
		return new ResponseEntity<List<Credit>>(credits, HttpStatus.OK);
	}
	
	@PostMapping("credits")
	public ResponseEntity<Credit> addCredit(@RequestBody Credit credit)
	{
		Credit item = creditService.save(credit);
		
		return new ResponseEntity<Credit>(item, HttpStatus.CREATED);
	}
	
	@PostMapping("addCreditByCustomer/{customerId}")
	public ResponseEntity<List<Credit>> addCreditByCustomerId(@RequestBody Credit credit, @PathVariable(value="customerId") int customerId)
	{
		List<Credit> credits = customerService.creditApplication(customerId);
		
		return new ResponseEntity<List<Credit>>(credits, HttpStatus.OK);
	}

	@PutMapping("credits/{creditId}")
	public ResponseEntity<Credit> updateCredit(@RequestBody Credit credit, @PathVariable(value="creditId") int id)
	{
		Optional<Credit> item = creditService.findById(id);
		
		if (item.isEmpty())
			throw new RuntimeException("sistemde kredi bulunamadi");
		
		Credit updated = creditService.save(credit);
		
		return new ResponseEntity<Credit>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("credits/{creditId}")
	public ResponseEntity<Credit> deleteCredit(@PathVariable(value="creditId") int id)
	{
		Optional<Credit> credit = creditService.findById(id);
		
		if (credit.isEmpty())
			throw new RuntimeException("sistemde kredi bulunamadi");
		
		creditService.delete(credit.get());
		
		return new ResponseEntity<Credit>(HttpStatus.NO_CONTENT);
	}
}
