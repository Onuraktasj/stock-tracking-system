package com.onuraktas.stocktrackingsystem.utils;

import com.onuraktas.stocktrackingsystem.dto.entity.UsersDto;
import com.onuraktas.stocktrackingsystem.entity.Users;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserUtils {

    public static Boolean validateUsersRequest(UUID usersId, UUID usersDtoUsersID) {
        return !Objects.isNull(usersId) && !Objects.isNull(usersDtoUsersID) && Objects.equals(usersId, usersDtoUsersID);
    }
}
