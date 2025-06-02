/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luzma
 */
package com.microservice.logistic.services;

import com.microservice.logistic.models.Product;
import com.microservice.logistic.repositories.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {

    @Autowired
    private productRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Product update(Integer id, Product updated) {
        Product existing = findById(id);
        if (existing != null) {
            existing.setSku(updated.getSku());
            existing.setDescripcion(updated.getDescripcion());
            existing.setMarca(updated.getMarca());
            existing.setPrecio(updated.getPrecio());
            existing.setProveedor(updated.getProveedor());
            return repository.save(existing);
        }
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}