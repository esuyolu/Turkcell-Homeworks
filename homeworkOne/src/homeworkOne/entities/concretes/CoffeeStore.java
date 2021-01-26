package homeworkOne.entities.concretes;

import homeworkOne.entities.abstracts.IEntity;

public class CoffeeStore implements IEntity {
	private int id;
	private String storeName;
	private String phone;
	private String location;
	
	public CoffeeStore(int id, String storeName, String phone, String location) 
	{
		this.id = id;
		this.storeName = storeName;
		this.phone = phone;
		this.location = location;
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

	public String getPhone() 
	{
		return phone;
	}

	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getLocation() 
	{
		return location;
	}

	public void setLocation(String location) 
	{
		this.location = location;
	}

	@Override
	public String toString() 
	{
		return String.format("no: " + id + " kahve dukkaninin ismi: " + storeName + " telefon: " + phone + " adres: " + location);
	}
}
