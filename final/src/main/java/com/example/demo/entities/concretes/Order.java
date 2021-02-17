package com.example.demo.entities.concretes;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.example.demo.entities.abstracts.IEntity;
import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Order implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_id")
	private int id;
	
	@Column(name="customer_id")
	private String customerId;
	
	@Column(name="cart_id")
	private int cartId;
	
	@Column(name="order_date")
	private LocalDate orderDate;
}
