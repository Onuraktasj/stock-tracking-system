package com.onuraktas.stocktrackingsystem.dto.entity;

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
public class AppUserDto {

    private UUID appUserId;

    private String name;

    private String phone;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}
