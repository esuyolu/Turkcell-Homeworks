package com.example.turkcell.business.concretes;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.turkcell.business.abstracts.IInvoiceService;
import com.example.turkcell.entities.concretes.Invoice;
import com.example.turkcell.repositories.InvoiceRepository;

@Service
public class InvoiceManager implements IInvoiceService {
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Override
	public List<Invoice> findAll() 
	{
		return invoiceRepository.findAll();
	}

	@Override
	public Optional<Invoice> findById(int id) 
	{
		return invoiceRepository.findById(id);
	}

	@Override
	public Invoice save(Invoice invoice)
	{
		return invoiceRepository.save(invoice);
	}

	@Override
	public void delete(Invoice invoice) 
	{
		invoiceRepository.delete(invoice);
	}

	@Override
	public List<Integer> findInvoicesIdsByCustomerId(int customerId)
	{
		return invoiceRepository.findInvoicesIdsByCustomerId(customerId);
	}

	@Override
	public List<Invoice> findInvoicesByCustomerId(int customerId)
	{
		return invoiceRepository.findInvoicesByCustomerId(customerId);
	}
}
