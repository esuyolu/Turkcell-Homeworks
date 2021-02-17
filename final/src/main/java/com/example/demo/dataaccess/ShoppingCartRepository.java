package com.example.demo.dataaccess;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entities.concretes.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
	@Query("select sc from ShoppingCart sc where sc.contactName = :contactName")
	public List<ShoppingCart> findByContactName(String contactName);
	
}
