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
import com.example.turkcell.business.abstracts.IPaymentService;
import com.example.turkcell.entities.concretes.Payment;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
	@Autowired
	IPaymentService paymentService;
	
	@GetMapping("/payments")
	public ResponseEntity<List<Payment>> getPayments()
	{
		List<Payment> payments = paymentService.findAll();
		
		if (payments.isEmpty())
			throw new RuntimeException("sistemde odeme bulunamadi");
		
		return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
	}
	
	@GetMapping("payments/{paymentId}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable(value="paymnetId") int id)
	{
		Optional<Payment> payment = paymentService.findById(id);
		
		if (payment.isEmpty())
			throw new RuntimeException("sistemde odeme bulunamadi");
		
		return new ResponseEntity<Payment>(payment.get(), HttpStatus.OK);
	}
	
	@GetMapping("payments/invoicesByInvoiceId/{invoiceId}")
	public ResponseEntity<List<Payment>> getPaymnetsByInvoiceId(@PathVariable(value="invoiceId") int invoiceId) 
	{
		List<Payment> payments = paymentService.findPaymnetsByInvoiceId(invoiceId);
		
		if (payments.isEmpty())
			throw new RuntimeException("sistemde odeme bulunamadi");
		
		return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
	}
	
	@GetMapping("payments/invoicesByCustomerId/{customerId}")
	public ResponseEntity<List<Payment>> getPaymnetsByCustomerId(@PathVariable(value="customerId") int customerId) 
	{
		List<Payment> payments = paymentService.findPaymnetsByCustomerId(customerId);
		
		if (payments.isEmpty())
			throw new RuntimeException("sistemde odeme bulunamadi");
		
		return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
	}
	
	
	@PostMapping("payments")
	public ResponseEntity<Payment> addPayment(@RequestBody Payment payment)
	{
		Payment item = paymentService.save(payment);
		
		return new ResponseEntity<Payment>(item, HttpStatus.CREATED);
	}

	@PutMapping("payments/{paymentId}")
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment, @PathVariable(value="paymentId") int id)
	{
		Optional<Payment> item = paymentService.findById(id);
		
		if (item.isEmpty())
			throw new RuntimeException("sistemde odeme bulunamadi");
		
		Payment updated = paymentService.save(payment);
		
		return new ResponseEntity<Payment>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("payments/{paymentId}")
	public ResponseEntity<Payment> deletePayment(@PathVariable(value="paymentId") int id)
	{
		Optional<Payment> payment = paymentService.findById(id);
		
		if (payment.isEmpty())
			throw new RuntimeException("sistemde odeme bulunamadi");
		
		paymentService.delete(payment.get());
		
		return new ResponseEntity<Payment>(HttpStatus.NO_CONTENT);
	}
}
