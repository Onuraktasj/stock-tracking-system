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
public class DeleteProductByIdResponse {

    private UUID productId;
    private Boolean isActive;
}
