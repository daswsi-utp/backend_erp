package com.microservice.crm.controllers;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.crm.entities.ArrivalMean;
import com.microservice.crm.repositories.ArrivalMeanRepository;


@RestController
@RequestMapping("/api/v1/crm/arrival_means")
public class ArrivalMeanController {

    private final ArrivalMeanRepository arrivalMeanRepository;

    public ArrivalMeanController(ArrivalMeanRepository arrivalMeanRepository) {
        this.arrivalMeanRepository = arrivalMeanRepository;
    }

    @GetMapping
    public ResponseEntity<List<ArrivalMean>> getAllArrivalMeans() {
        List<ArrivalMean> means = arrivalMeanRepository.findAll();
        return ResponseEntity.ok(means);
    }
}
