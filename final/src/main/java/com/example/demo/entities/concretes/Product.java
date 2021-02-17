package com.example.demo.entities.concretes;

import java.io.Serializable;
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
@Table(name="products")
public class Product implements IEntity, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int id;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="quantity_per_unit")
	private String quantityPerUnit;
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="units_in_stock")
	private int unitsInStock;
	
	@Column(name="units_on_order")
	private int unitsOnOrder;
	
	@Column(name = "discontinued")
	private int discontinued;
	
	
	// methods
	
	public boolean removeStock()
	{
		return removeStock(1);
	}
	
	public boolean removeStock(int value)
	{
		if (unitsInStock > 0) {
			unitsInStock -= value;
			unitsOnOrder += value;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addStock()
	{
		unitsInStock++;
	}
	
	public void addStock(int value)
	{
		unitsInStock += value;
	}
}