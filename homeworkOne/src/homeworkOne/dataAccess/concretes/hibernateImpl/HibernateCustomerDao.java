package homeworkOne.dataAccess.concretes.hibernateImpl;

import java.util.ArrayList;
import java.util.List;

import homeworkOne.dataAccess.abstracts.ICustomerDao;
import homeworkOne.entities.concretes.Customer;

public class HibernateCustomerDao implements ICustomerDao {

	@Override
	public void add(Customer customer) 
	{
		System.out.println("Hibernate kullanilarak musteri eklendi.");
	}

	@Override
	public void update(Customer customer) 
	{
		System.out.println("Hibernate kullanilarak musteri guncellendi.");
	}

	@Override
	public void delete(Customer customer)
	{
		System.out.println("Hibernate kullanilarak musteri silindi.");
	}

	@Override
	public List<Customer> getAll() 
	{
		Customer customer1 = new Customer(1, "12345678901", "Esma", "Suyolu", "esmasuyolu@gmail.com", 1996);
		Customer customer2 = new Customer(1, "10987654321", "Meryem", "Karatas", "meryemkaratas@gmail.com", 2000);
		
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer1);
		customers.add(customer2);
		
		System.out.println("Hibernate kullanildi.");
		
		return customers;
	}


}
