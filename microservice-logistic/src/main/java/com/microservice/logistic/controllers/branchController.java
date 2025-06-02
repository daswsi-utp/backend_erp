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
@RequestMapping("/api/v1/logistic/branches")
public class branchController {
    @Autowired
    private branchService branchService;

    @GetMapping
    public List<Branch> getAll() {
        return branchService.findAll();
    }

    @GetMapping("/{id}")
    public Branch getById(@PathVariable Integer id) {
        return branchService.findById(id);
    }

    @PostMapping
    public Branch create(@RequestBody Branch branch) {
        return branchService.save(branch);
    }

    @PutMapping("/{id}")
    public Branch update(@PathVariable Integer id, @RequestBody Branch updated) {
        return branchService.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        branchService.delete(id);
    }
}
