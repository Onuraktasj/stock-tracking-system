package com.onuraktas.stocktrackingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Builder
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID supplierId;

    private String supplierName;

    private String phone;

    private String email;

    @Builder.Default
    private Boolean isActive = true;
}
