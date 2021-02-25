package com.example.turkcell.business.concretes;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.turkcell.business.abstracts.ICreditService;
import com.example.turkcell.entities.concretes.Credit;
import com.example.turkcell.repositories.CreditRepository;

@Service
public class CreditManager implements ICreditService {
	@Autowired
	private CreditRepository creditRepository;
	
	@Override
	public List<Credit> findAll() 
	{
		return creditRepository.findAll();
	}

	@Override
	public Optional<Credit> findById(int id) 
	{
		return creditRepository.findById(id);
	}

	@Override
	public Credit save(Credit credit) 
	{
		return creditRepository.save(credit);
	}

	@Override
	public void delete(Credit credit) 
	{
		creditRepository.delete(credit);
	}

	@Override
	public List<Credit> findCreditsByCustomerId(int customerId) 
	{
		return creditRepository.findCreditsByCustomerId(customerId);
	}

	@Override
	public List<Integer> findCreditsIdsIdsByCustomerId(int customerId) 
	{
		return creditRepository.findCreditsIdsIdsByCustomerId(customerId);
	}
}