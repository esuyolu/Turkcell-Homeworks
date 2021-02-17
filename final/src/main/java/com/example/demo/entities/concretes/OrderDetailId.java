package com.example.demo.entities.concretes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderDetailId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="product_id")
	private int productId;
	
	@Column(name="order_id")
	private int orderId;
}
