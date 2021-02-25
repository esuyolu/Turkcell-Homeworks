package com.example.turkcell.business.abstracts;

import java.util.List;
import java.util.Optional;
import com.example.turkcell.entities.concretes.Payment;

public interface IPaymentService {
	List<Payment> findAll();
	Optional<Payment> findById(int id);
	Payment save(Payment payment);
	void delete(Payment payment);
	List<Integer> findPaymnetsIdsIdsByInvoiceId(int invoiceId);
	List<Payment> findPaymnetsByInvoiceId(int invoiceId);
	List<Payment> findPaymnetsByCustomerId(int customerId);
}
