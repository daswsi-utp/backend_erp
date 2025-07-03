package com.microservice.crm.services;

import com.microservice.crm.dto.CreateProductDTO;
import com.microservice.crm.dto.ProductDTO;
import com.microservice.crm.entities.Product;
import com.microservice.crm.repositories.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    

    public Page<ProductDTO> searchProducts(String searchParams, List<String> fields, PageRequest pageRequest) {
        Page<Product> productPage = productRepository.searchByFields(searchParams, fields, pageRequest);
        
        List<ProductDTO> productDTOs = productPage.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return productPage.map(product -> mapToDTO(product)); // Mapea el contenido a DTO
    }


    public List<ProductDTO> getAllProductsList(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        List<Product> activeProducts = productRepository.findByStatus(1); // Filtro por 'status'
        System.out.println("Active Products: " + activeProducts.size());
        return activeProducts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
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
    
    public List<ProductDTO> formatProductData(Page<ProductDTO> productPage) {
        return productPage.getContent().stream().map(this::formatProduct).collect(Collectors.toList());
    }

    private ProductDTO formatProduct(ProductDTO productDTO) {
        return productDTO; // Puede agregar lógica adicional si se requiere
    }
}
