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
import com.example.turkcell.business.abstracts.IInvoiceService;
import com.example.turkcell.entities.concretes.Invoice;

@RestController
@RequestMapping("/api/v1")
public class InvoiceController {
	@Autowired
	IInvoiceService invoiceService;
	
	@GetMapping("/invoices")
	public ResponseEntity<List<Invoice>> getInvoices()
	{
		List<Invoice> invoices = invoiceService.findAll();
		
		if (invoices.isEmpty())
			throw new RuntimeException("sistemde fatura bulunamadi");
		
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}
	
	@GetMapping("invoices/{invoiceId}")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable(value="invoiceId") int id)
	{
		Optional<Invoice> invoice = invoiceService.findById(id);
		
		if (invoice.isEmpty())
			throw new RuntimeException("sistemde fatura bulunamadi");
		
		return new ResponseEntity<Invoice>(invoice.get(), HttpStatus.OK);
	}
	
	@GetMapping("invoices/customer/{customerId}")
	public ResponseEntity<List<Invoice>> getInvoicesByCustomerId(int customerId)
	{
		List<Invoice> invoices = invoiceService.findInvoicesByCustomerId(customerId);
		
		if (invoices.isEmpty())
			throw new RuntimeException("sistemde fatura bulunamadi");
		
		return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
	}
	
	@PostMapping("invoices")
	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice)
	{
		Invoice item = invoiceService.save(invoice);
		
		return new ResponseEntity<Invoice>(item, HttpStatus.CREATED);
	}

	@PutMapping("invoices/{invoiceId}")
	public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice, @PathVariable(value="invoiceId") int id)
	{
		Optional<Invoice> item = invoiceService.findById(id);
		
		if (item.isEmpty())
			throw new RuntimeException("sistemde fatura bulunamadi");
		
		Invoice updated = invoiceService.save(invoice);
		
		return new ResponseEntity<Invoice>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("invoices/{invoiceId}")
	public ResponseEntity<Invoice> deleteInvoice(@PathVariable(value="invoiceId") int id)
	{
		Optional<Invoice> invoice = invoiceService.findById(id);
		
		if (invoice.isEmpty())
			throw new RuntimeException("sistemde fatura bulunamadi");
		
		invoiceService.delete(invoice.get());
		
		return new ResponseEntity<Invoice>(HttpStatus.NO_CONTENT);
	}
}
