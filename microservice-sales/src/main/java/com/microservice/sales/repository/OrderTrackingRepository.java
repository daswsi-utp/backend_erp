package com.microservice.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.sales.model.OrderTracking;

public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Long> {

}
