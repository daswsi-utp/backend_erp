package com.microservice.crm.services;

import com.microservice.crm.dto.ClientDTO;
import com.microservice.crm.dto.CreateClientDTO;
import com.microservice.crm.entities.Client;
import com.microservice.crm.entities.ClientState;
import com.microservice.crm.entities.Product;
import com.microservice.crm.entities.Reason;
import com.microservice.crm.entities.ArrivalMean;
import com.microservice.crm.repositories.ClientRepository;
import com.microservice.crm.repositories.ClientStateRepository;
import com.microservice.crm.repositories.ProductRepository;
import com.microservice.crm.repositories.ReasonRepository;
import com.microservice.crm.repositories.ArrivalMeanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class ClientService {
	
	private final ClientRepository clientRepository;
    private final ClientStateRepository clientStateRepository;
    private final ProductRepository productRepository;
    private final ReasonRepository reasonRepository;
    private final ArrivalMeanRepository arrivalMeanRepository;

}
