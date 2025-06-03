package com.microservice.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.microservice.sales.model.OrderTracking;
import com.microservice.sales.repository.OrderTrackingRepository;

@Service
public class OrderTrackingService {
	
	@Autowired
	
	OrderTrackingRepository orderTrackingRepository;
	
	public List<OrderTracking> getOrderTrackings(){return orderTrackingRepository.findAll();}
	public Optional<OrderTracking> getOrderTrackingsById(Long id){return orderTrackingRepository.findById(id);}
	public OrderTracking createOrderTracking(OrderTracking orderTracking) {return orderTrackingRepository.save(orderTracking);}
	public OrderTracking updateOrderTracking(OrderTracking orderTracking) {return orderTrackingRepository.save(orderTracking);}
	public void deleteOrderTracking(Long id) {orderTrackingRepository.deleteById(id);}

}
