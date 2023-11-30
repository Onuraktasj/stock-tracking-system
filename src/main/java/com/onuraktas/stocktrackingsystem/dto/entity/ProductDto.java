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
public class ProductDto {

    private UUID productId;

    private String productName;

    private String description;

    private Integer amount;



}
