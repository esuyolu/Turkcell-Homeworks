package homeworkOne.business.concretes;

import homeworkOne.dataAccess.abstracts.IOrderDao;
import homeworkOne.entities.concretes.Customer;
import homeworkOne.entities.dtos.OrderDetails;

public class OrderService {
	private IOrderDao<Customer, OrderDetails> orderDao;

	public OrderService(IOrderDao<Customer, OrderDetails> orderDao) 
	{
		this.orderDao = orderDao;
	}
	
	public void sales(Customer customer, OrderDetails orderDetails)
	{
		orderDao.sales(customer, orderDetails);
	}
}
