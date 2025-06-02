package com.microservice.crm.services;

import com.microservice.crm.dto.ProductDTO;
import com.microservice.crm.entities.Product;
import com.microservice.crm.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


}
