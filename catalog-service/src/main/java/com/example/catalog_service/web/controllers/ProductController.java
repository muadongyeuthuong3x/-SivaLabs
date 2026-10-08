package com.example.catalog_service.web.controllers;
import org.springframework.web.bind.annotation.RestController;
import com.example.catalog_service.domain.PagedResult;
import com.example.catalog_service.domain.Product;
import com.example.catalog_service.domain.ProductNotFoundException;
import com.example.catalog_service.domain.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("api/products")

public class ProductController {
    private final ProductService productService;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public PagedResult<Product> getMethodName(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {
          return productService.getProducts(pageNo);
    }

      @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        log.info("Fetching product for code: {}", code);
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
    
}
