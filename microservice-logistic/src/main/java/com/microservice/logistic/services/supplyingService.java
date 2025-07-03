/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luzma
 */
package com.microservice.logistic.services;

import com.microservice.logistic.models.Supplying;
import com.microservice.logistic.repositories.supplyingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class supplyingService {

    @Autowired
    private supplyingRepository repository;

    public List<Supplying> findAll() {
        return repository.findAll();
    }

    public Supplying findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Supplying save(Supplying supplying) {
        return repository.save(supplying);
    }

    public Supplying update(Integer id, Supplying updated) {
        Supplying existing = findById(id);
        if (existing != null) {
            existing.setProducto(updated.getProducto());
            existing.setProveedor(updated.getProveedor());
            existing.setCantidad(updated.getCantidad());
            existing.setPrecioUnitario(updated.getPrecioUnitario());
            existing.setFechaCompra(updated.getFechaCompra());
            existing.setComprobante(updated.getComprobante());
            existing.setObservacion(updated.getObservacion());
            return repository.save(existing);
        }
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
