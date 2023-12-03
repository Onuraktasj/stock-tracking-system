package com.onuraktas.stocktrackingsystem.utils;

import java.util.Objects;
import java.util.UUID;

public class AppUserUtils {

    public static Boolean validateUsersRequest(UUID usersId, UUID usersDtoUsersID) {
        return !Objects.isNull(usersId) && !Objects.isNull(usersDtoUsersID) && Objects.equals(usersId, usersDtoUsersID);
    }
}
