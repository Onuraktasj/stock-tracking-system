package com.onuraktas.stocktrackingsystem.entity;

import com.onuraktas.stocktrackingsystem.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID appUserId;

    private String name;

    private String phone;

    private String email;

    private Boolean status;

    private Role role;
}
