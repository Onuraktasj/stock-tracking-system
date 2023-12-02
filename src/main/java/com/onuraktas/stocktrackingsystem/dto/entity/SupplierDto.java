package com.onuraktas.stocktrackingsystem.dto.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class SupplierDto {

    private UUID supplierId;

    private String supplierName;

    private String phone;

    private String email;

    private Boolean isActive;
}
