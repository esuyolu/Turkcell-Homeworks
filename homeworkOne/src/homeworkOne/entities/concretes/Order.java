package homeworkOne.entities.concretes;

import homeworkOne.entities.abstracts.IEntity;

public class Order implements IEntity {
	private int id;
	private int customerId;
	private int coffeeStroreId;
	private int productId;
	
	public Order(int id, int customerId, int coffeeStroreId, int productId)
	{
		this.id = id;
		this.customerId = customerId;
		this.coffeeStroreId = coffeeStroreId;
		this.productId = productId;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public int getCustomerId() 
	{
		return customerId;
	}

	public void setCustomerId(int customerId) 
	{
		this.customerId = customerId;
	}

	public int getCoffeeStroreId() 
	{
		return coffeeStroreId;
	}

	public void setCoffeeStroreId(int coffeeStroreId) 
	{
		this.coffeeStroreId = coffeeStroreId;
	}

	public int getProductId() 
	{
		return productId;
	}

	public void setProductId(int productId) 
	{
		this.productId = productId;
	}
}
