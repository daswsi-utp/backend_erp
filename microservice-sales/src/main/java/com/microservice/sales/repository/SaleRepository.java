package com.microservice.sales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.sales.model.DeliveryStatus;
import com.microservice.sales.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	 List<Sale> findByDeliveryStatus(DeliveryStatus status);
	
}
