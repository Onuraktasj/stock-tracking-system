package com.onuraktas.stocktrackingsystem.dto.entity;

import com.onuraktas.stocktrackingsystem.entity.enums.Role;
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
public class UsersDto {

    private UUID usersId;

    private String name;

    @Enumerated
    private Role role;
}
