package com.example.turkcell.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.turkcell.entities.concretes.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	@Query("select i.id from Invoice i where i.customerId = :customerId")
	List<Integer> findInvoicesIdsByCustomerId(int customerId);
	
	@Query("select i from Invoice i where i.customerId = :customerId")
	List<Invoice> findInvoicesByCustomerId(int customerId);
}
