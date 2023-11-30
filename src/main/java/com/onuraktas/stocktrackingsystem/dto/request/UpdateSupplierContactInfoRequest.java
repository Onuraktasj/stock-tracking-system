package com.onuraktas.stocktrackingsystem.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class UpdateSupplierContactInfoRequest {

    private String phone;
    private String email;
}
