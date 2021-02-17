package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dataaccess.OrderRepository;
import com.example.demo.dataaccess.ShoppingCartRepository;
import com.example.demo.entities.concretes.Order;
import com.example.demo.entities.concretes.ShoppingCart;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@GetMapping("orders")
	public ResponseEntity<List<Order>> getOrders()
	{
		List<Order> orders = orderRepository.findAll();
		
		if (orders.isEmpty())
			throw new RuntimeException("no shopping cart was found");
		
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
	@GetMapping("orders/{orderId}")
	public ResponseEntity<Order> readOrderById(@PathVariable(value="orderId") int id)
	{
		Optional<Order> order = orderRepository.findById(id);
		
		if (order.isEmpty())
			throw new RuntimeException("no order cart was found");
		
		return new ResponseEntity<Order>(order.get(), HttpStatus.OK);
	}
	
	@PostMapping("orders")
	public ResponseEntity<Order> add(@RequestBody Order order)
	{
		Order item = orderRepository.save(order);
		
		return new ResponseEntity<Order>(item, HttpStatus.CREATED);
	}
	
	@PutMapping("orders/{orderId}")
	public ResponseEntity<Order> update(@RequestBody Order order, @PathVariable(value="orderId") int id)
	{
		Optional<Order> item = orderRepository.findById(id);
		
		if (item.isEmpty())
			throw new RuntimeException("no order cart was found");
		
		Order updated = orderRepository.save(order);
		
		return new ResponseEntity<Order>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("orders/{orderId}")
	public ResponseEntity<Order> delete(@PathVariable(value="orderId") int id)
	{
		Optional<Order> order = orderRepository.findById(id);
		
		if (order.isEmpty())
			throw new RuntimeException("no order cart was found");
		
		orderRepository.delete(order.get());
		
		return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("orders/customers/{customerId}")
	public List<Order> getOrdersFromCustomerId(@PathVariable(value="customerId") String customerId)
	{
		List<Order> orderList = orderRepository.getOrdersFromCustomerId(customerId);
		
		if (orderList.isEmpty())
			throw new RuntimeException("no order was found");
		
		return orderList;
	}
	
	@GetMapping("orders/carts/{cartId}")
	public List<Order> getOrdersFromShoppingCartId(@PathVariable(value="cartId") int cartId)
	{
		List<Order> orderList = orderRepository.getOrdersFromShoppingCartId(cartId);
		
		if (orderList.isEmpty())
			throw new RuntimeException("no order was found");
		
		return orderList;
	}
	
	@GetMapping("orders/{orderId}/carts/{cartId}")
	public Map<String, Boolean> orderedOrPending(@PathVariable(value="orderId") int orderId, @PathVariable(value="cartId") int cartId)
	{
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		Optional<Order> order = orderRepository.findById(orderId);
		String customerId = order.get().getCustomerId();
		Map<String, Boolean> response = new HashMap<>();
		
		if (cart.isEmpty())
			throw new RuntimeException("no shopping cart was found");
		
		if (order.isEmpty())
			throw new RuntimeException("no order was found");
		
		if (cart.get().getStatus().equals("ordered")) {
			response.put("order of customer(" + customerId + ") has been received", Boolean.TRUE);
			shoppingCartRepository.delete(cart.get());
		}
		else
			response.put("order of customer(" + customerId + ") has not been completed", Boolean.FALSE);
		
		return response;
	}
}
