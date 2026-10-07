package com.example.catalog_service.domain;

import java.util.List;

public record PagedResult<T>(
        List<T> data,
        long totalElements,
        int totalPages,
        int pageNumber,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious

) {}