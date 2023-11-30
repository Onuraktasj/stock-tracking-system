package com.onuraktas.stocktrackingsystem.dto.request;

import com.onuraktas.stocktrackingsystem.entity.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class UpdateCategoryNameRequest {

    private String categoryName;
}
