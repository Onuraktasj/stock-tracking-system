package com.onuraktas.stocktrackingsystem.service;

import com.onuraktas.stocktrackingsystem.dto.entity.UsersDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateUsersRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateUsersContactInfoRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateUsersResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UsersService {

    CreateUsersResponse createUsers(CreateUsersRequest createUsersRequest);

    UsersDto getUsers(UUID usersId);
    List<UsersDto> getAllUsers();

    ResponseEntity<UsersDto> updateUsers(UUID usersId, UsersDto usersDto);

    UsersDto updateUsersName(UUID usersId,String name);

    UsersDto updateUsersContactInfo(UUID usersId, UpdateUsersContactInfoRequest request);

    void deleteUsers(UUID usersId);

}
