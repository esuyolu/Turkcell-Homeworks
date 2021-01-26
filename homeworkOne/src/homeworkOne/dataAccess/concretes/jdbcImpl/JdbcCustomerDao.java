package homeworkOne.dataAccess.concretes.jdbcImpl;

import java.util.ArrayList;
import java.util.List;
import homeworkOne.dataAccess.abstracts.ICustomerDao;
import homeworkOne.entities.concretes.Customer;

public class JdbcCustomerDao implements ICustomerDao {

	@Override
	public void add(Customer customer) 
	{
		System.out.println("JDBC kullanilarak musteri eklendi.");
	}

	@Override
	public void update(Customer customer) 
	{
		System.out.println("JDBC kullanilarak musteri guncellendi.");
	}

	@Override
	public void delete(Customer customer)
	{
		System.out.println("JDBC kullanil12345678901arak musteri silindi.");
	}

	@Override
	public List<Customer> getAll() 
	{
		Customer customer1 = new Customer(1, "12345678901", "Esma", "Suyolu", "esmasuyolu@gmail.com", 1996);
		Customer customer2 = new Customer(1, "10987654321", "Meryem", "Karatas", "meryemkaratas@gmail.com", 2000);
		
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer1);
		customers.add(customer2);
		
		System.out.println("JDBC kullanildi.");
		
		return customers;
	}

	
	
}
