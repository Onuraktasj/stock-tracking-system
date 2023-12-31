package com.onuraktas.stocktrackingsystem.dto.request;

import com.onuraktas.stocktrackingsystem.entity.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Builder
public class CreateAppUserDto {

    private String name;

    private String phone;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}
