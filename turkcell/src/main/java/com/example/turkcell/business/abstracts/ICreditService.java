package com.example.turkcell.business.abstracts;

import java.util.List;
import java.util.Optional;
import com.example.turkcell.entities.concretes.Credit;

public interface ICreditService {
	List<Credit> findAll();
	Optional<Credit> findById(int id);
	Credit save(Credit credit);
	void delete(Credit credit);
	List<Credit> findCreditsByCustomerId(int customerId);
	List<Integer> findCreditsIdsIdsByCustomerId(int customerId);
}
