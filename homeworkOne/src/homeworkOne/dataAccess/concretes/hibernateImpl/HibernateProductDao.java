package homeworkOne.dataAccess.concretes.hibernateImpl;

import java.util.ArrayList;
import java.util.List;

import homeworkOne.dataAccess.abstracts.IProductDao;
import homeworkOne.entities.concretes.Product;

public class HibernateProductDao implements IProductDao {

	@Override
	public void add(Product product) 
	{
		System.out.println("Hibernate kullanilarak urun eklendi.");
	}

	@Override
	public void update(Product product) 
	{
		System.out.println("Hibernate kullanilarak urun guncellendi.");
	}

	@Override
	public void delete(Product product) 
	{
		System.out.println("Hibernate kullanilarak urun silindi.");
	}

	@Override
	public List<Product> getAll() 
	{
		Product product1 = new Product(1, "Flat White", 12.75);
		Product product2 = new Product(2, "Espresso", 8);
		Product product3 = new Product(3, "Cold Brew", 13);
		Product product4 = new Product(4, "Caramel Macchiato", 16);
		
		List<Product> products = new ArrayList<Product>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product4);
		
		System.out.println("Hibernate kullanildi.");
		
		return products;
	}

}
