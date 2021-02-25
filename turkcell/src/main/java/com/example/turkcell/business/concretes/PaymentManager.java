package com.example.turkcell.business.concretes;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.turkcell.business.abstracts.IPaymentService;
import com.example.turkcell.entities.concretes.Payment;
import com.example.turkcell.repositories.PaymentRepository;

@Service
public class PaymentManager implements IPaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<Payment> findAll() 
	{
		return paymentRepository.findAll();
	}

	@Override
	public Optional<Payment> findById(int id) 
	{
		return paymentRepository.findById(id);
	}

	@Override
	public Payment save(Payment payment) 
	{
		return paymentRepository.save(payment);
	}

	@Override
	public void delete(Payment payment) 
	{
		paymentRepository.delete(payment);
	}

	@Override
	public List<Integer> findPaymnetsIdsIdsByInvoiceId(int invoiceId) 
	{
		return paymentRepository.findPaymnetsIdsIdsByInvoiceId(invoiceId);
	}

	@Override
	public List<Payment> findPaymnetsByInvoiceId(int invoiceId) 
	{
		return paymentRepository.findPaymnetsByInvoiceId(invoiceId);
	}

	@Override
	public List<Payment> findPaymnetsByCustomerId(int customerId) 
	{
		return paymentRepository.findPaymnetsByCustomerId(customerId);
	}
}
