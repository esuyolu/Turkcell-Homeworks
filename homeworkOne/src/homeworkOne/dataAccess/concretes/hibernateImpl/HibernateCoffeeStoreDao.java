package homeworkOne.dataAccess.concretes.hibernateImpl;

import java.util.ArrayList;
import java.util.List;

import homeworkOne.dataAccess.abstracts.ICoffeeStoreDao;
import homeworkOne.entities.concretes.CoffeeStore;
import homeworkOne.entities.concretes.Nero;
import homeworkOne.entities.concretes.Starbucks;

public class HibernateCoffeeStoreDao implements ICoffeeStoreDao {

	@Override
	public void add(CoffeeStore coffeeStore) 
	{
		System.out.println("Hibernate kullanilarak kahve dukkani eklendi.");
	}

	@Override
	public void update(CoffeeStore coffeeStore) 
	{
		System.out.println("Hibernate kullanilarak kahve dukkani guncellendi.");
	}

	@Override
	public void delete(CoffeeStore coffeeStore) 
	{
		System.out.println("Hibernate kullanilarak kahve dukkani silindi.");
	}

	@Override
	public List<CoffeeStore> getAll() 
	{
		CoffeeStore coffeeStore1 = new Starbucks(1, "123456789", "Istanbul");
		CoffeeStore coffeeStore2 = new Nero(2, "987654321", "Ankara");
		
		List<CoffeeStore> coffeeStores = new ArrayList<CoffeeStore>();
		
		coffeeStores.add(coffeeStore1);
		coffeeStores.add(coffeeStore2);
		
		System.out.println("Hibernate kullanildi.");
		
		return coffeeStores;
	}

}
