package com.onuraktas.stocktrackingsystem.repository;

import com.onuraktas.stocktrackingsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
