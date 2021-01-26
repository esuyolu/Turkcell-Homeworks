package homeworkOne.dataAccess.concretes.hibernateImpl;

import homeworkOne.dataAccess.abstracts.IEDevletLogin;
import homeworkOne.entities.concretes.Customer;
import homeworkOne.entities.concretes.EDevletLogin;

public class HibernateEDevletLoginDao implements IEDevletLogin<EDevletLogin, Customer> {
	@Override
	public void validation(EDevletLogin login, Customer customer) 
	{
		if (login.getIdentityNumber() == customer.getIdentityNumber() && login.getFirstName() == customer.getFirstName() && 
				login.getLastName() == customer.getLastName() && login.getYearOfBirth() == customer.getYearOfBirth()) {
			System.out.println("Hibernate kullanildi.");
			System.out.println("Giris basarili!!");
		}
		else {
			System.out.println("Hibernate kullanildi.");
			System.out.println("Bilgilerinizi yanlis girdiniz!! Lutfen tekrar deneyiniz.");
		}
	}

}