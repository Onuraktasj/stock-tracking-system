package com.onuraktas.stocktrackingsystem.mapper;

import com.onuraktas.stocktrackingsystem.dto.entity.AppUserDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateAppUserRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateAppUserResponse;
import com.onuraktas.stocktrackingsystem.entity.AppUser;

import java.util.List;
import java.util.Objects;

public class AppUserMapper {

    public static AppUserDto toDto(AppUser appUser){
        if (Objects.isNull(appUser))
            return null;

        return AppUserDto.builder()
                .appUserId(appUser.getAppUserId())
                .name(appUser.getName())
                .phone(appUser.getPhone())
                .email(appUser.getEmail())
                .role(appUser.getRole())
                .build();
    }

    public static AppUser toEntity(AppUserDto appUserDto){
        if (Objects.isNull(appUserDto))
            return null;

        return AppUser.builder()
                .appUserId(appUserDto.getAppUserId())
                .name(appUserDto.getName())
                .phone(appUserDto.getPhone())
                .email(appUserDto.getEmail())
                .role(appUserDto.getRole())
                .build();
    }

    public static AppUser toEntity(CreateAppUserRequest createAppUserRequest){
        if (Objects.isNull(createAppUserRequest))
            return null;

        return AppUser.builder()
                .name(createAppUserRequest.getName())
                .phone(createAppUserRequest.getPhone())
                .email(createAppUserRequest.getEmail())
                .role(createAppUserRequest.getRole())
                .build();
    }

    public static CreateAppUserResponse toCreateUsersResponse(AppUser appUser){
        if (Objects.isNull(appUser))
            return null;
        return CreateAppUserResponse.builder()
                .usersId(appUser.getAppUserId())
                .name(appUser.getName())
                .phone(appUser.getPhone())
                .email(appUser.getEmail())
                .role(appUser.getRole())
                .build();
    }

    public static List<AppUserDto> toDtoList(List<AppUser> appUserList){
        return appUserList.stream().parallel()
                .map(AppUserMapper::toDto)
                .toList();
    }
}
