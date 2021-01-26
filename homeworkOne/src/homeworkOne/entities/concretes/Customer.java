package homeworkOne.entities.concretes;

import homeworkOne.entities.abstracts.IEntity;

public class Customer implements IEntity {
	private int id;
	private String identityNumber;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private int yearOfBirth;
	
	public Customer(int id, String identityNumber, String firstName, String lastName, String emailAddress, int yearOfBirth) 
	{
		this.id = id;
		this.identityNumber = identityNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.yearOfBirth = yearOfBirth;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getIdentityNumber() 
	{
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) 
	{
		this.identityNumber = identityNumber;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getEmailAddress() 
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) 
	{
		this.emailAddress = emailAddress;
	}

	public int getYearOfBirth()
	{
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth)
	{
		this.yearOfBirth = yearOfBirth;
	}	
}
