package com.example.catalog_service.domain;
import java.math.BigDecimal;


public record Product(String code2, String name, String description, String imageUrl, BigDecimal price) {}