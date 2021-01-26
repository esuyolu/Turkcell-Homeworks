package homeworkOne.business.concretes;

import java.util.List;

import homeworkOne.dataAccess.abstracts.ICustomerDao;
import homeworkOne.entities.concretes.Customer;

public class CustomerService {
	private ICustomerDao customerDao;

	public CustomerService(ICustomerDao customerDao) 
	{
		this.customerDao = customerDao;
	}
	
	public void add(Customer customer) 
	{
		customerDao.add(customer);
	}
	
	public void update(Customer customer) 
	{
		customerDao.update(customer);
	}
	
	public void delete(Customer customer) 
	{
		customerDao.delete(customer);
	}
	
	public List<Customer> getAll()
	{
		return customerDao.getAll();
	}
}
