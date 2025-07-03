package com.microservice.sales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.sales.model.OrderTracking;
import com.microservice.sales.service.OrderTrackingService;

@RestController
@RequestMapping("/api/v1/sales/ordertracking")

public class OrderTrackingController {
	
	@Autowired
	private OrderTrackingService orderTrackingService;
	
	@GetMapping
	public ResponseEntity<List<OrderTracking>> getOrderTrackings(){return ResponseEntity.ok(orderTrackingService.getOrderTrackings());}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderTracking> getOrderTrackingsById(@PathVariable Long id)
	{
		return orderTrackingService.getOrderTrackingsById(id)
				.map(ResponseEntity::ok)
				.orElseGet(()-> ResponseEntity.notFound().build());
	} 
	
	@PostMapping
	public OrderTracking createOrderTracking(@RequestBody OrderTracking orderTracking) {return orderTrackingService.createOrderTracking(orderTracking);}
	
	@PutMapping
	public ResponseEntity<OrderTracking> updateOrderTracking(@RequestBody OrderTracking orderTracking){return ResponseEntity.ok(orderTrackingService.updateOrderTracking(orderTracking));}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrderTracking(@PathVariable Long id)
	{
		orderTrackingService.deleteOrderTracking(id);
		return ResponseEntity.noContent().build();
	}
}
