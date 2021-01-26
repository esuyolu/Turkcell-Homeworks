package homeworkOne;

import java.util.List;

import homeworkOne.business.concretes.CampaignService;
import homeworkOne.business.concretes.CoffeeStoreService;
import homeworkOne.business.concretes.CustomerService;
import homeworkOne.business.concretes.EDevletLoginService;
import homeworkOne.business.concretes.OrderService;
import homeworkOne.business.concretes.ProductService;
import homeworkOne.dataAccess.concretes.hibernateImpl.HibernateCampaignDao;
import homeworkOne.dataAccess.concretes.hibernateImpl.HibernateOrderDao;
import homeworkOne.dataAccess.concretes.jdbcImpl.JdbcCoffeeStroreDao;
import homeworkOne.dataAccess.concretes.jdbcImpl.JdbcCustomerDao;
import homeworkOne.dataAccess.concretes.jdbcImpl.JdbcEDevletLoginDao;
import homeworkOne.dataAccess.concretes.jdbcImpl.JdbcProductDao;
import homeworkOne.entities.concretes.Campaign;
import homeworkOne.entities.concretes.CoffeeStore;
import homeworkOne.entities.concretes.Customer;
import homeworkOne.entities.concretes.EDevletLogin;
import homeworkOne.entities.concretes.Product;
import homeworkOne.entities.concretes.StarbucksCampaign;
import homeworkOne.entities.dtos.OrderDetails;

public class Main {

	public static void main(String[] args) 
	{
		CoffeeStoreService coffeeStoreService = new CoffeeStoreService(new JdbcCoffeeStroreDao());
		List<CoffeeStore> coffeeStores = coffeeStoreService.getAll();
		
		System.out.println("****************************************");
		
		for (CoffeeStore coffeeStroe : coffeeStores) {
			System.out.println(coffeeStroe);
		}
		
		System.out.println("****************************************");
		
		CustomerService customerService = new CustomerService(new JdbcCustomerDao());
		List<Customer> customers = customerService.getAll();
		
		System.out.println("****************************************");
		
		ProductService productService = new ProductService(new JdbcProductDao());
		List<Product> products = productService.getAll();
		
		System.out.println("****************************************");
		
		OrderDetails orderDetails = new OrderDetails(1, coffeeStores.get(0).getStoreName(), products.get(0).getProductName(),  products.get(0).getUnitPrice());
		
		OrderService orderService = new OrderService(new HibernateOrderDao());
		orderService.sales(customers.get(0), orderDetails);
		
		System.out.println("****************************************");
		
		Campaign campaign = new StarbucksCampaign(1);
		EDevletLogin eDevletLogin = new EDevletLogin(1, "12345678901", "Esma", "Suyolu", 1996);
		
		if (coffeeStores.get(0).getStoreName().equals(campaign.getStoreName())) {
			EDevletLoginService eDevletLoginService = new EDevletLoginService(new JdbcEDevletLoginDao());
			eDevletLoginService.validation(eDevletLogin, customers.get(0));
			
			System.out.println("****************************************");
		
			CampaignService campaignService = new CampaignService(new HibernateCampaignDao());
			campaignService.campaignDetails(campaign);
		}	
	}
}
