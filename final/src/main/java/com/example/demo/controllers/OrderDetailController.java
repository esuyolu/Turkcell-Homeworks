package com.example.demo.controllers;

import java.util.List;
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

import com.example.demo.dataaccess.OrderDetailRepository;
import com.example.demo.entities.concretes.OrderDetail;

@RestController
@RequestMapping("/api/v1")
public class OrderDetailController {
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@GetMapping("orderDetails")
	public ResponseEntity<List<OrderDetail>> getOrderDetails()
	{
		List<OrderDetail> orderDetails = orderDetailRepository.findAll();
		
		if (orderDetails.isEmpty())
			throw new RuntimeException("no order detail was found");
		
		return new ResponseEntity<List<OrderDetail>>(orderDetails, HttpStatus.OK);
	}
	
	@GetMapping("orderDetailsById/{orderId}/{productId}")
	public ResponseEntity<OrderDetail> readOrderDetailById(@PathVariable(value="orderId") int orderId, @PathVariable(value="productId") int productId)
	{
		OrderDetail orderDetail = orderDetailRepository.getOrderDetailById(orderId, productId);
		
		if (orderDetail == null)
			throw new RuntimeException("no order detail was found");
		
		return new ResponseEntity<OrderDetail>(orderDetail, HttpStatus.OK);
	}
	
	@PostMapping("orderDetails")
	public ResponseEntity<OrderDetail> add(@RequestBody OrderDetail orderDetail)
	{
		OrderDetail item = orderDetailRepository.save(orderDetail);
		
		return new ResponseEntity<OrderDetail>(item, HttpStatus.CREATED);
	}
	
	@PutMapping("orderDetailsById/{orderId}/{productId}")
	public ResponseEntity<OrderDetail> updateById(@RequestBody OrderDetail orderDetail, @PathVariable(value="orderId") int orderId, @PathVariable(value="productId") int productId)
	{
		OrderDetail item = orderDetailRepository.getOrderDetailById(orderId, productId);
		
		if (item == null)
			throw new RuntimeException("no order detail was found");
		
		OrderDetail updated = orderDetailRepository.save(orderDetail);
		
		return new ResponseEntity<OrderDetail>(updated, HttpStatus.OK);
	}
		
	@DeleteMapping("orderDetailsById/{orderId}/{productId}")
	public ResponseEntity<OrderDetail> deleteByProductId(@PathVariable(value="orderId") int orderId, @PathVariable(value="productId") int productId)
	{
		OrderDetail orderDetail = orderDetailRepository.getOrderDetailById(orderId, productId);
		
		if (orderDetail == null)
			throw new RuntimeException("no order detail was found");
		
		orderDetailRepository.delete(orderDetail);
		
		return new ResponseEntity<OrderDetail>(HttpStatus.NO_CONTENT);
	}
}
