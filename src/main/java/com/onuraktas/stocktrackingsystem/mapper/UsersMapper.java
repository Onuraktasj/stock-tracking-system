package com.onuraktas.stocktrackingsystem.mapper;

import com.onuraktas.stocktrackingsystem.dto.entity.UsersDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateUsersRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateUsersResponse;
import com.onuraktas.stocktrackingsystem.entity.Users;

import java.util.List;
import java.util.Objects;

public class UsersMapper {

    public static UsersDto toDto(Users users){
        if (Objects.isNull(users))
            return null;

        return UsersDto.builder()
                .usersId(users.getUsersId())
                .name(users.getName())
                .phone(users.getPhone())
                .email(users.getEmail())
                .role(users.getRole())
                .build();
    }

    public static Users toEntity(UsersDto usersDto){
        if (Objects.isNull(usersDto))
            return null;
        return Users.builder()
                .usersId(usersDto.getUsersId())
                .name(usersDto.getName())
                .phone(usersDto.getPhone())
                .email(usersDto.getEmail())
                .role(usersDto.getRole())
                .build();
    }

    public static Users toEntity(CreateUsersRequest createUsersRequest){
        if (Objects.isNull(createUsersRequest))
            return null;

        return Users.builder()
                .name(createUsersRequest.getName())
                .phone(createUsersRequest.getPhone())
                .email(createUsersRequest.getEmail())
                .role(createUsersRequest.getRole())
                .build();
    }

    public static CreateUsersResponse toCreateUsersResponse(Users users){
        if (Objects.isNull(users))
            return null;
        return CreateUsersResponse.builder()
                .usersId(users.getUsersId())
                .name(users.getName())
                .phone(users.getPhone())
                .email(users.getEmail())
                .role(users.getRole())
                .build();
    }

    public static List<UsersDto> toDtoList(List<Users> usersList){
        return usersList.stream().parallel()
                .map(UsersMapper::toDto)
                .toList();
    }





}
