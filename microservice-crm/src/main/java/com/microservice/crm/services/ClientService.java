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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
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

    // Método para normalizar teléfono (sin espacios y con prefijo)
    private String normalizePhone(String phone, String countryCode) {
        if (phone == null) return null;
        String phoneAux = phone.replaceAll("\\s", "").trim();

        if (!phoneAux.startsWith("+")) {
            if (phoneAux.matches("^[531].*")) {
                phoneAux = "+" + phoneAux;
            } else if (countryCode != null && !countryCode.isEmpty()) {
                phoneAux = countryCode + phoneAux;
            }
        }
        return phoneAux;
    }

    public Optional<ClientDTO> createClient(CreateClientDTO dto) {
        String phoneAux = normalizePhone(dto.getPhone(), dto.getCountryCode());

        
        boolean exists = clientRepository.existsByPhoneAndProductId(phoneAux, dto.getProductId());
        if (exists) {
            return Optional.empty(); 
        }

        Client client = new Client();
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setEmail(dto.getEmail());
        client.setPhone(phoneAux);
        client.setWhatsapp(phoneAux);
        client.setCountry(dto.getCountry());
        client.setCountryCode(dto.getCountryCode());
        client.setCity(dto.getCity());
        client.setAddress(dto.getAddress());
        client.setCompanyName(dto.getCompanyName());
        client.setJobTitle(dto.getJobTitle());
        client.setBirthDate(dto.getBirthDate());
        client.setNotes(dto.getNotes());
        client.setEmployeeId(dto.getEmployeeId());
        client.setCreatedAt(LocalDateTime.now());

        // Setear relaciones
        clientStateRepository.findById(dto.getClientStateId()).ifPresent(client::setClientState);
        productRepository.findById(dto.getProductId()).ifPresent(client::setProduct);
        reasonRepository.findById(dto.getReasonId()).ifPresent(client::setReason);
        arrivalMeanRepository.findById(dto.getArrivalMeanId()).ifPresent(client::setArrivalMean);

        Client saved = clientRepository.save(client);
        return Optional.of(mapToDTO(saved));
    }

    public Optional<ClientDTO> getClient(Long id) {
        return clientRepository.findById(id).map(this::mapToDTO);
    }
    
    private ClientDTO mapToDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .whatsapp(client.getWhatsapp())
                .country(client.getCountry())
                .countryCode(client.getCountryCode())
                .city(client.getCity())
                .address(client.getAddress())
                .companyName(client.getCompanyName())
                .jobTitle(client.getJobTitle())
                .birthDate(client.getBirthDate())
                .notes(client.getNotes())
                .employeeId(client.getEmployeeId())
                .clientStateId(client.getClientState() != null ? client.getClientState().getId() : null)
                .clientStateName(client.getClientState() != null ? client.getClientState().getName() : null)
                .productId(client.getProduct() != null ? client.getProduct().getId() : null)
                .reasonName(client.getReason() != null ? client.getReason().getName() : null)
                .arrivalMeanName(client.getArrivalMean() != null ? client.getArrivalMean().getName() : null)
                .build();
    }
}
