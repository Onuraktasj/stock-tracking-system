package com.onuraktas.stocktrackingsystem.dto.response;

import com.onuraktas.stocktrackingsystem.entity.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class CreateUsersResponse {

    private UUID usersId;

    private String name;

    private String phone;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String status;

}
