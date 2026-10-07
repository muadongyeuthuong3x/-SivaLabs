package com.example.catalog_service.web.controllers;
import org.springframework.web.bind.annotation.RestController;
import com.example.catalog_service.domain.PagedResult;
import com.example.catalog_service.domain.Product;
import com.example.catalog_service.domain.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/products")

public class ProductController {
    private final ProductService productService;
    
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public PagedResult<Product> getMethodName(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {
          return productService.getProducts(pageNo);
    }
    
}
