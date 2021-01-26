package homeworkOne.entities.concretes;

import homeworkOne.entities.abstracts.IEntity;

public class Product implements IEntity {
	private int id;
	private String productName;
	private double unitPrice;
	
	public Product(int id, String productName, double unitPrice) 
	{
		this.id = id;
		this.productName = productName;
		this.unitPrice = unitPrice;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getProductName() 
	{
		return productName;
	}

	public void setProductName(String productName) 
	{
		this.productName = productName;
	}

	public double getUnitPrice() 
	{
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice)
	{
		this.unitPrice = unitPrice;
	}
	
}
