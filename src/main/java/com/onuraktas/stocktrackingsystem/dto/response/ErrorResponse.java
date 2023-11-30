package com.onuraktas.stocktrackingsystem.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class ErrorResponse {

    private String message;
}
