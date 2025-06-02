/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luzma
 */
package com.microservice.logistic.services;

import com.microservice.logistic.models.Inventory;
import com.microservice.logistic.repositories.inventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class inventoryService {

    @Autowired
    private inventoryRepository repository;

    public List<Inventory> findAll() {
        return repository.findAll();
    }

    public Inventory findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Inventory save(Inventory inventory) {
        // Calcular el estado del stock antes de guardar
        inventory.setEstadoStock(calcularEstadoStock(inventory.getStock()));
        return repository.save(inventory);
    }

    public Inventory update(Integer id, Inventory updated) {
        Inventory existing = findById(id);
        if (existing != null) {
            existing.setProducto(updated.getProducto());
            existing.setStock(updated.getStock());
            existing.setStockMinimo(updated.getStockMinimo());
            existing.setStockMaximo(updated.getStockMaximo());
            existing.setUbicacion(updated.getUbicacion());
            existing.setSucursal(updated.getSucursal());
            // Calcular estado basado en el nuevo stock
            existing.setEstadoStock(calcularEstadoStock(updated.getStock()));
            return repository.save(existing);
        }
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    // Método privado para calcular el estado del stock
    private String calcularEstadoStock(int stock) {
        if (stock < 10) {
            return "Bajo";
        } else if (stock <= 50) {
            return "Regular";
        } else {
            return "Alto";
        }
    }
}