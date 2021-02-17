package com.example.demo.entities.concretes;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.example.demo.entities.abstracts.IEntity;
import lombok.Data;

@Data
@Entity
@Table(name="order_details")
public class OrderDetail implements IEntity {
	@EmbeddedId
	private OrderDetailId id;
	
	@Column(name="product_id", insertable=false, updatable=false)
	private int productId;
	
	@Column(name="order_id", insertable=false, updatable=false)
	private int orderId;
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="discount")
	private double discount;
}
