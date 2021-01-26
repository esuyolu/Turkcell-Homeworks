package homeworkOne.dataAccess.concretes.jdbcImpl;

import homeworkOne.dataAccess.abstracts.IOrderDao;
import homeworkOne.entities.concretes.Customer;
import homeworkOne.entities.dtos.OrderDetails;

public class JdbcOrderDao implements IOrderDao<Customer, OrderDetails> {

	@Override
	public void sales(Customer customer, OrderDetails orderDetails) 
	{
		System.out.println("JDBC kullanildi.");
		System.out.println(customer.getId() + " nolu musteri " + orderDetails.getStoreName() + " isimli kahve dukkanindan " +
				orderDetails.getProductName() + " isimli urunu " + orderDetails.getPrice() + " lira ucret vererek satin almistir.");
	}
}
