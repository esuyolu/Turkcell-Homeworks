package com.example.demo.dataaccess;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entities.concretes.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query("select o from Order o where o.customerId = :customerId")
	public List<Order> getOrdersFromCustomerId(String customerId);
	
	@Query("select o from Order o where o.cartId = :cartId")
	public List<Order> getOrdersFromShoppingCartId(int cartId);
}
