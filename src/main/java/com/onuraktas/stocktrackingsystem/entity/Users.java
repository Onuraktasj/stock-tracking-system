package com.onuraktas.stocktrackingsystem.entity;

import com.onuraktas.stocktrackingsystem.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID usersId;

    private String name;

    private Role role;
}
