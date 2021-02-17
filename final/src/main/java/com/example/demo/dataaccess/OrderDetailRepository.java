package com.example.demo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entities.concretes.OrderDetail;
import com.example.demo.entities.concretes.OrderDetailId;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
	@Query("select o from OrderDetail o where o.orderId = :orderId and o.productId = :productId")
	public OrderDetail getOrderDetailById(int orderId, int productId);
	
}
