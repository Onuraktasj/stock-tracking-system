package com.onuraktas.stocktrackingsystem.repository;

import com.onuraktas.stocktrackingsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
}
