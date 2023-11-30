package com.onuraktas.stocktrackingsystem.repository;


import com.onuraktas.stocktrackingsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {


}
