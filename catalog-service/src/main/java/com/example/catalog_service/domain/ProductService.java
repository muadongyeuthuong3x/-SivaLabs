package com.example.catalog_service.domain;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.catalog_service.ApplicationProperties;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;

@Service 
@Transactional 
public class ProductService {
    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties; 
    public ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }

    public PagedResult<Product> getProducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo < 0 ? 0 : pageNo; // Ensure pageNo is not negative
        Pageable pageable = PageRequest.of(pageNo, applicationProperties.pageSize(), sort);
        Page<Product> products = productRepository.findAll(pageable).map(ProductMapper::toProduct);
        PagedResult<Product> pagedResult = new PagedResult<>(
            products.getContent(),
            products.getTotalElements(),
            products.getTotalPages(),
            products.getNumber() +1,
            products.isFirst(),
            products.isLast(),
            products.hasNext(),
            products.hasPrevious()
        );
        return pagedResult;
    }

    public Optional<Product> getProductByCode(String code) {
        return productRepository.findByCode(code).map(ProductMapper::toProduct);
    }
}
