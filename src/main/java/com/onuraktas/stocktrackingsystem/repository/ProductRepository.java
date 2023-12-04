package com.onuraktas.stocktrackingsystem.repository;


import com.onuraktas.stocktrackingsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllByProductIdIn(List<UUID> productIdList);
}
