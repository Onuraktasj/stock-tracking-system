package com.onuraktas.stocktrackingsystem.repository;

import com.onuraktas.stocktrackingsystem.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}
