package com.onuraktas.stocktrackingsystem.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@SuperBuilder
public class CreateProductResponse {

    private UUID productId;

    private String productName;

    private String status;
}
