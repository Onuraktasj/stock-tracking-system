package com.onuraktas.stocktrackingsystem.service;

import com.onuraktas.stocktrackingsystem.dto.entity.AppUserDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateAppUserRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateAppUserContactInfoRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateAppUserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface AppUserService {

    CreateAppUserResponse createAppUser(CreateAppUserRequest createAppUserRequest);

    AppUserDto getAppUserById(UUID usersId);
    List<AppUserDto> getAllActiveAppUsers();

    ResponseEntity<AppUserDto> updateAppUserById(UUID usersId, AppUserDto appUserDto);

    AppUserDto updateAppUserNameById(UUID usersId, String name);

    AppUserDto updateAppUserContactInfoById(UUID usersId, UpdateAppUserContactInfoRequest request);

    void deleteAppUserById(UUID usersId);

}
