/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luzma
 */
package com.microservice.logistic.services;

import com.microservice.logistic.models.Branch;
import com.microservice.logistic.repositories.branchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class branchService {

    @Autowired
    private branchRepository repository;

    public List<Branch> findAll() {
        return repository.findAll();
    }

    public Branch findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Branch save(Branch b) {
        return repository.save(b);
    }

    public Branch update(Integer id, Branch updated) {
        Branch existing = findById(id);
        if (existing == null) return null;

        existing.setNombre(updated.getNombre());
        existing.setDireccion(updated.getDireccion());
        existing.setTelefono(updated.getTelefono());
        existing.setEncargado(updated.getEncargado());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}