package com.example.turkcell.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.turkcell.entities.concretes.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	@Query("select p.id from Payment p where p.invoiceId = :invoiceId")
	List<Integer> findPaymnetsIdsIdsByInvoiceId(int invoiceId);
	
	@Query("select p from Payment p where p.invoiceId = :invoiceId")
	List<Payment> findPaymnetsByInvoiceId(int invoiceId);
	
	@Query("select p from Payment p where p.customerId = :customerId")
	List<Payment> findPaymnetsByCustomerId(int customerId);
}
