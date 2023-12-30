package com.onuraktas.stocktrackingsystem.impl;

import com.onuraktas.stocktrackingsystem.entity.CategoryProductRel;
import com.onuraktas.stocktrackingsystem.repository.CategoryProductRelRepository;
import com.onuraktas.stocktrackingsystem.service.CategoryProductRelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryProductRelServiceImpl implements CategoryProductRelService {

    private final CategoryProductRelRepository categoryProductRelRepository;

    public CategoryProductRelServiceImpl(CategoryProductRelRepository categoryProductRelRepository) {
        this.categoryProductRelRepository = categoryProductRelRepository;
    }

    @Override
    public List<UUID> deleteCategoryProductRelByCategoryId(UUID categoryId) {
        List<CategoryProductRel> categoryProductRelList = categoryProductRelRepository.findAllByCategoryIdAndIsActive(categoryId, Boolean.TRUE);
        categoryProductRelList.forEach(categoryProductRel -> categoryProductRel.setIsActive(Boolean.FALSE));
        categoryProductRelRepository.saveAll(categoryProductRelList);

        return categoryProductRelList.stream().map(CategoryProductRel::getProductId).toList();
    }
}
