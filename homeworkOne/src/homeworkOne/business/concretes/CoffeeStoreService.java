package homeworkOne.business.concretes;

import java.util.List;

import homeworkOne.dataAccess.abstracts.ICoffeeStoreDao;
import homeworkOne.entities.concretes.CoffeeStore;

public class CoffeeStoreService {
	private ICoffeeStoreDao coffeeStoreDao;

	public CoffeeStoreService(ICoffeeStoreDao coffeeStoreDao)
	{
		this.coffeeStoreDao = coffeeStoreDao;
	}
	
	public void add(CoffeeStore coffeeStore) 
	{
		coffeeStoreDao.add(coffeeStore);
	}
	
	public void update(CoffeeStore coffeeStore) 
	{
		coffeeStoreDao.update(coffeeStore);
	}
	
	public void delete(CoffeeStore coffeeStore) 
	{
		coffeeStoreDao.delete(coffeeStore);
	}
	
	public List<CoffeeStore> getAll()
	{
		return coffeeStoreDao.getAll();
	}
}
