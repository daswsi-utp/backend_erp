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
    
    public ClientService(ClientRepository clientRepository,
            ClientStateRepository clientStateRepository,
            ProductRepository productRepository,
            ReasonRepository reasonRepository,
            ArrivalMeanRepository arrivalMeanRepository) {
				this.clientRepository = clientRepository;
				this.clientStateRepository = clientStateRepository;
				this.productRepository = productRepository;
				this.reasonRepository = reasonRepository;
				this.arrivalMeanRepository = arrivalMeanRepository;
    		}
    public ClientDTO createClient(CreateClientDTO dto) {
        Client client = new Client();
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setWhatsapp(dto.getWhatsapp());
        client.setCountry(dto.getCountry());
        client.setCountryCode(dto.getCountryCode());
        client.setCity(dto.getCity());
        client.setAddress(dto.getAddress());
        client.setCompanyName(dto.getCompanyName());
        client.setJobTitle(dto.getJobTitle());
        client.setBirthDate(dto.getBirthDate());
        client.setNotes(dto.getNotes());
        client.setEmployeeId(dto.getEmployeeId());

        clientStateRepository.findById(dto.getClientStateId())
                .ifPresent(client::setClientState);

        productRepository.findById(dto.getProductId())
                .ifPresent(client::setProduct);

        reasonRepository.findById(dto.getReasonId())
                .ifPresent(client::setReason);

        arrivalMeanRepository.findById(dto.getArrivalMeanId())
                .ifPresent(client::setArrivalMean);

        Client saved = clientRepository.save(client);

        return mapToDTO(saved);
    }
}
