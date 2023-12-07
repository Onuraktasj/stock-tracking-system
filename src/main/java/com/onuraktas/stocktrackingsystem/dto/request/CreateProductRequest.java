package com.onuraktas.stocktrackingsystem.dto.request;

import com.onuraktas.stocktrackingsystem.dto.general.SimpleCategory;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class CreateProductRequest {

    private String productName;
    private String description;
    private Integer amount;
    private List<SimpleCategory> categoryList;
}
