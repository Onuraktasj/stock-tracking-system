package com.onuraktas.stocktrackingsystem.dto.request;


import com.onuraktas.stocktrackingsystem.entity.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class CreateAppUserRequest {

    private String name;

    private String phone;

    private String email;

    private Role role;
}
