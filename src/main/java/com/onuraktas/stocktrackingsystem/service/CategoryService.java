package com.onuraktas.stocktrackingsystem.service;

import com.onuraktas.stocktrackingsystem.dto.request.CreateCategoryRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateCategoryResponse;

public interface CategoryService {
    CreateCategoryResponse createCategory(CreateCategoryRequest categoryRequest);

}
