package com.microservice.crm.services;

import com.microservice.crm.dto.CreateProductDTO;
import com.microservice.crm.dto.ProductDTO;
import com.microservice.crm.entities.Product;
import com.microservice.crm.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> activeProducts = productRepository.findByStatus(1); // 1 = activo
        return activeProducts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    public ProductDTO createProduct(CreateProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPricePen(dto.getPricePen());
        product.setPriceDollar(dto.getPriceDollar());
        product.setStart(dto.getStart());
        product.setCode(dto.getCode());
        product.setStatus(dto.getStatus());
        product.setCover(dto.getCover());
        product.setDescription(dto.getDescription());

        Product saved = productRepository.save(product);
        return mapToDTO(saved);
    }


    private ProductDTO mapToDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .pricePen(product.getPricePen())
                .priceDollar(product.getPriceDollar())
                .start(product.getStart())
                .code(product.getCode())
                .status(product.getStatus())
                .cover(product.getCover())
                .description(product.getDescription())
                .build();
    }
}
