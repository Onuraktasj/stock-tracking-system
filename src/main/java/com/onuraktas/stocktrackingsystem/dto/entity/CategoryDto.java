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
public class CategoryDto {

    private UUID categoriesId;

    private String categoriesName;



}
