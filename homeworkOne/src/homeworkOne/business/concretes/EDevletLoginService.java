package homeworkOne.business.concretes;

import homeworkOne.dataAccess.abstracts.IEDevletLogin;
import homeworkOne.entities.concretes.Customer;
import homeworkOne.entities.concretes.EDevletLogin;

public class EDevletLoginService {
	private IEDevletLogin<EDevletLogin, Customer> eDevletLogin;

	public EDevletLoginService(IEDevletLogin<EDevletLogin, Customer> eDevletLogin) 
	{
		this.eDevletLogin = eDevletLogin;
	}
	
	public void validation(EDevletLogin login, Customer customer)
	{
		eDevletLogin.validation(login, customer);
	}
}
