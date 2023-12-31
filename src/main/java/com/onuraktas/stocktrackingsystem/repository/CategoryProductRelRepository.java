package com.onuraktas.stocktrackingsystem.repository;

import com.onuraktas.stocktrackingsystem.entity.CategoryProductRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryProductRelRepository extends JpaRepository<CategoryProductRel, UUID> {

    List<CategoryProductRel> findAllByCategoryIdAndIsActive(UUID categoryId, Boolean isActive);
    List<CategoryProductRel> findAllByProductIdAndIsActive(UUID productId, Boolean isActive);
    List<CategoryProductRel> findAllByProductIdInAndIsActive(List<UUID> productIdList, Boolean isActive);
}
