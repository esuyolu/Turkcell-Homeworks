package com.example.turkcell.business.abstracts;

import java.util.List;
import java.util.Optional;
import com.example.turkcell.entities.concretes.Invoice;

public interface IInvoiceService {
	List<Invoice> findAll();
	Optional<Invoice> findById(int id);
	Invoice save(Invoice invoice);
	void delete(Invoice invoice);
	List<Integer> findInvoicesIdsByCustomerId(int customerId);
	List<Invoice> findInvoicesByCustomerId(int customerId);
}
