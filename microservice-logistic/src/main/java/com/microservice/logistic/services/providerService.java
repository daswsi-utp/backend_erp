/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sebastian
 */
package com.microservice.logistic.services;

import com.microservice.logistic.models.Provider;
import com.microservice.logistic.repositories.providerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class providerService {

    @Autowired
    private providerRepository repository;

    public List<Provider> findAll() {
        return repository.findAll();
    }

    public Provider findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Provider save(Provider provider) {
        return repository.save(provider);
    }

    public Provider update(Integer id, Provider updated) {
        Provider existing = findById(id);
        if (existing != null) {
            existing.setNombre(updated.getNombre());
            existing.setContacto(updated.getContacto());
            existing.setTelefono(updated.getTelefono());
            existing.setEmail(updated.getEmail());
            existing.setDireccion(updated.getDireccion());
            return repository.save(existing);
        }
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}