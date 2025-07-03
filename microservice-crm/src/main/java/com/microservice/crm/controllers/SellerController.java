package com.microservice.crm.controllers;

import com.microservice.crm.dto.ClientStateStats;
import com.microservice.crm.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1/crm/")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("sellers/{sellerId}/stats")
    public List<ClientStateStats> getSellerStats(@PathVariable Long sellerId) {
        return sellerService.getSellerStats(sellerId);
    }
}
