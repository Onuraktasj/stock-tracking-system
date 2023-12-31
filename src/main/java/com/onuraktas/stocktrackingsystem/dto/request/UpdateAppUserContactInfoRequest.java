package com.onuraktas.stocktrackingsystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class UpdateAppUserContactInfoRequest {

    private String phone;
    private String email;
}
