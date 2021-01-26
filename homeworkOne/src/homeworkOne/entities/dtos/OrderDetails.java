package homeworkOne.entities.dtos;

import homeworkOne.entities.abstracts.IDto;

public class OrderDetails implements IDto {
	private int id;
	private String storeName;
	private String productName;
	private double price;
	
	public OrderDetails(int id, String storeName, String productName, double price) 
	{
		this.id = id;
		this.storeName = storeName;
		this.productName = productName;
		this.price = price;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getStoreName() 
	{
		return storeName;
	}

	public void setStoreName(String storeName) 
	{
		this.storeName = storeName;
	}

	public String getProductName() 
	{
		return productName;
	}

	public void setProductName(String productName) 
	{
		this.productName = productName;
	}

	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double price) 
	{
		this.price = price;
	}
}
