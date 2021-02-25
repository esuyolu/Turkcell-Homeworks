package com.example.turkcell.entities.concretes;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.example.turkcell.entities.abstracts.IEntity;
import lombok.Data;

@Data
@Entity
@Table(name="payments")
public class Payment implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="payment_id")
	private int id;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="invoice_id")
	private int invoiceId;
	
	@Column(name="payment_amount")
	private BigDecimal paymentAmount;
	
	@Column(name="payment_date")
	private LocalDate paymentDate;

}
