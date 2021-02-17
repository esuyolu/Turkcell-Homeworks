package com.example.demo.entities.concretes;

import java.util.HashMap;
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
@Table(name="shoppingcarts")
public class ShoppingCart implements IEntity {
	public static final String ORDERED = "ordered";
    public static final String PENDING = "pending";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cart_id")
	private int id;
	
	@Column(name="status")
	private String status;
	
	@Column(name="contact_name")
	private String contactName;
	
	@Column(name="total_price")
	private double totalPrice;
	
	private HashMap<Integer, Product> productMaps;
	
	private HashMap<Integer, Integer> productQuantities; 
	// key -> productId, value -> quantity

	
	// methods
	
	public Product getProductFromId(int productId)
	{
		return productMaps.get(productId);
	}
	
	public void addProduct(Product product)
	{
		Product fromCart = productMaps.get(product.getId());
		
		if (fromCart == null) {
			productMaps.put(product.getId(), product);
		}
	}
		
	public void addProductQuantity(Product product)
	{
		addProductQuantity(product, 1);
	}
	
	public void addProductQuantity(Product product, int n)
	{
		int productId = product.getId();
		
		if (productQuantities.containsKey(productId)) {
			int quantity = productQuantities.get(productId);
			quantity += n;
			productQuantities.put(productId, quantity);
		}
		
		else {
			productQuantities.put(productId, 1);
		}
		
		totalPrice += n * product.getUnitPrice();
	}
	
	public void removeProduct(int productId)
	{
		if (productMaps.containsKey(productId)) {
			productMaps.remove(productId);
		}
	}
	
	public void removeProductQuantity(Product product)
	{
		removeProductQuantity(product, 1);
	}
	
	public void removeProductQuantity(Product product, int n)
	{
		int productId = product.getId();
		
		if (this.productQuantities.containsKey(productId)) {
			int quantity = this.productQuantities.get(productId);
			quantity -= n;
			
			if (quantity < 1) {
				this.productQuantities.remove(productId);
                this.removeProduct(productId);
			}
			else {
				this.productQuantities.put(productId, quantity);
			}
			
			this.totalPrice -= n * product.getUnitPrice();
		}
	}
}
