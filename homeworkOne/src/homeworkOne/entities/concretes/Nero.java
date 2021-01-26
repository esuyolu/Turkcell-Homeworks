package homeworkOne.entities.concretes;

public class Nero extends CoffeeStore {

	public Nero(int id, String storeName, String phone, String location) 
	{
		super(id, storeName, phone, location);
	}

	public Nero(int id, String phone, String location) 
	{
		this(id, "Nero", phone, location);
	}
}
