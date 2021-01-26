package homeworkOne.entities.concretes;

public class Starbucks extends CoffeeStore {

	public Starbucks(int id, String storeName, String phone, String location)
	{
		super(id, storeName, phone, location);
	}
	
	public Starbucks(int id, String phone, String location)
	{
		this(id, "Starbucks", phone, location);
	}

}
