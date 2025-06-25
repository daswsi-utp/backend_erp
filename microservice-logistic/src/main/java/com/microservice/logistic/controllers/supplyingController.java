package com.microservice.logistic.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.logistic.services.*;
import com.microservice.logistic.models.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sebastian Ticlavilca
 */
@RestController
@RequestMapping("/api/v1/logistic/supplyings")
public class supplyingController {
    @Autowired
    private supplyingService supplyingService;

    @GetMapping
    public List<Supplying> getAll() {
        return supplyingService.findAll();
    }

    @GetMapping("/{id}")
    public Supplying getById(@PathVariable Integer id) {
        return supplyingService.findById(id);
    }

    @PostMapping
    public Supplying create(@RequestBody Supplying supplying) {
        return supplyingService.save(supplying);
    }

    @PutMapping("/{id}")
    public Supplying update(@PathVariable Integer id, @RequestBody Supplying updated) {
        return supplyingService.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        supplyingService.delete(id);
    }
}
