package com.onuraktas.stocktrackingsystem.dto.general;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class SimpleCategory {

    private UUID categoryId;
}
