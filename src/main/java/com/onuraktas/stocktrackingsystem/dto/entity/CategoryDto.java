package com.onuraktas.stocktrackingsystem.dto.entity;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class CategoryDto implements Serializable {

    private UUID categoryId;
    private String categoryName;
    private Boolean isActive;
}
