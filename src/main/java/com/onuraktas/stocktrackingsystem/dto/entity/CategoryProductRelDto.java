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
public class CategoryProductRelDto {

    private UUID id;
    private UUID categoryId;
    private UUID productId;
    private Boolean isActive;
}

