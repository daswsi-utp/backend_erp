
package com.microservice.crm.repositories;

import com.microservice.crm.entities.Client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	boolean existsByPhoneAndProductId(String phone, Long productId);
	List<Client> findByPhone(String phone);
}
