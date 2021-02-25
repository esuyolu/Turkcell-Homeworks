package com.example.turkcell.entities.concretes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.example.turkcell.entities.abstracts.IEntity;
import lombok.Data;


@Data
@Entity
@Table(name="invoices")
public class Invoice implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="invoice_id")
	private int id;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="invoice_due_date")
	private LocalDate invoiceDueDate;
	
	@Column(name="invoice_cost")
	private BigDecimal invoiceCost;
	
	@Column(name="payment_list")
	@ElementCollection(targetClass=Integer.class)
	private List<Integer> paymentList; // kredilerin idleri tutulur
	
}
