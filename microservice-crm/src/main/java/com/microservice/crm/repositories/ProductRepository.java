package com.microservice.crm.repositories;

import com.microservice.crm.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findByStatus(int status);

    default Page<Product> searchByFields(String searchParams, List<String> searchFields, Pageable pageable) {
        Specification<Product> spec = Specification.where(null);

        for (String field : searchFields) {
            switch (field.toLowerCase()) {
                case "name":
                    spec = spec.and((root, query, criteriaBuilder) ->
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + searchParams.toLowerCase() + "%"));
                    break;
                case "description":
                    spec = spec.and((root, query, criteriaBuilder) ->
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + searchParams.toLowerCase() + "%"));
                    break;
                case "code":
                    spec = spec.and((root, query, criteriaBuilder) ->
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("code")), "%" + searchParams.toLowerCase() + "%"));
                    break;
                default:
                    break;
            }
        }

        return findAll(spec, pageable); 
    }

    @Query("SELECT COUNT(p) FROM Product p WHERE " +
            "(LOWER(p.name) LIKE LOWER(CONCAT('%', :searchParams, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :searchParams, '%')) " +
            "OR LOWER(p.code) LIKE LOWER(CONCAT('%', :searchParams, '%')))")
    long countBySearchParams(String searchParams);
}
