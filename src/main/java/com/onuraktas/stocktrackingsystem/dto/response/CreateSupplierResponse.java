package com.onuraktas.stocktrackingsystem.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class CreateSupplierResponse {

    private UUID supplierId;

    private String supplierName;

    private String phone;

    private String email;

    private String status;

    private Boolean isActive;
}
