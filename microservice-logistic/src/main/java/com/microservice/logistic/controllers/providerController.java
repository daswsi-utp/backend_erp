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
@RequestMapping("/api/v1/logistic/providers")
public class providerController {
    @Autowired
    private providerService providerService;

    @GetMapping
    public List<Provider> getAll() {
        return providerService.findAll();
    }

    @GetMapping("/{id}")
    public Provider getById(@PathVariable Integer id) {
        return providerService.findById(id);
    }

    @PostMapping
    public Provider create(@RequestBody Provider provider) {
        return providerService.save(provider);
    }

    @PutMapping("/{id}")
    public Provider update(@PathVariable Integer id, @RequestBody Provider updated) {
        return providerService.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        providerService.delete(id);
    }
}