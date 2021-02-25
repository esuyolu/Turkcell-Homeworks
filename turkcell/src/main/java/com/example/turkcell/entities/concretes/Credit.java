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
@Table(name="credits")
public class Credit implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="credit_id")
	private int id;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="date_credit_take")
	private LocalDate dateCreditTake;
	
	@Column(name="date_credit_repayment")
	private LocalDate dateCreditRepayment;
	
	@Column(name="max_money")
	private BigDecimal maxMoney;
	
	@Column(name="credit_application_per_month")
	private boolean creditApplicationPerMonth;
	
	@Column(name="credit_limit_per_year")
	private BigDecimal creditLimitPerYear;
	
	
	
}
