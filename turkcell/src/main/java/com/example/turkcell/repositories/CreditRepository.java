package com.example.turkcell.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.turkcell.entities.concretes.Credit;

public interface CreditRepository extends JpaRepository<Credit, Integer> {
	@Query("select c.id from Credit c where c.customerId = :customerId")
	List<Integer> findCreditsIdsIdsByCustomerId(int customerId);
	
	@Query("select c from Credit c where c.customerId = :customerId")
	List<Credit> findCreditsByCustomerId(int customerId);

}
