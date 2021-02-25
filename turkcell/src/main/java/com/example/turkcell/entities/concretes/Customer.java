package com.example.turkcell.entities.concretes;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.example.turkcell.entities.abstracts.IEntity;
import com.example.turkcell.entities.abstracts.PaidType;
import com.example.turkcell.entities.abstracts.SubscriptionType;
import lombok.Data;

@Data
@Entity
@Table(name="customers")
public class Customer implements IEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_id")
	private int id;
	
	@Column(name="identity_number")
	private String identityNumber;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="msisdn")
	private String msisdn;
	
	@Column(name="address")
	private String address;
	
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	@Column(name="paid_type")
	private PaidType paidType;
	
	@Column(name="date_of_subscription")
	private LocalDate dateOfSubscription;
	
	@Enumerated(EnumType.STRING)
	@Column(name="subscription_type")
	private SubscriptionType subscriptionType;
	
	@Column(name="is_black_listed")
	private boolean isBlackListed;
	
	@Column(name="credit_list")
	@ElementCollection(targetClass=Integer.class)
	private List<Integer> creditList; // kredilerin idleri tutulur
	
	@Column(name="invoice_list")
	@ElementCollection(targetClass=Integer.class)
	private List<Integer> invoiceList; // faturalarin idleri tutulur
}
