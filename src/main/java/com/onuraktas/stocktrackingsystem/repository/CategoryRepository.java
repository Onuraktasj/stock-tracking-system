package com.onuraktas.stocktrackingsystem.repository;

import com.onuraktas.stocktrackingsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Category findByCategoryName(String categoryName);

    List<Category> findAllByCategoryIdInAndIsActive(List<UUID> categoryIds, Boolean isActive);
}
