package com.onuraktas.stocktrackingsystem.impl;

import com.onuraktas.stocktrackingsystem.dto.entity.AppUserDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateAppUserRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateAppUserContactInfoRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateAppUserResponse;
import com.onuraktas.stocktrackingsystem.entity.AppUser;
import com.onuraktas.stocktrackingsystem.entity.enums.Status;
import com.onuraktas.stocktrackingsystem.mapper.AppUserMapper;
import com.onuraktas.stocktrackingsystem.message.AppUserMessages;
import com.onuraktas.stocktrackingsystem.repository.AppUserRepository;
import com.onuraktas.stocktrackingsystem.service.AppUserService;
import com.onuraktas.stocktrackingsystem.utils.AppUserUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }
    @Override
    public CreateAppUserResponse createAppUser(CreateAppUserRequest createAppUserRequest) {
        AppUser appUser = this.appUserRepository.save(AppUserMapper.toEntity(createAppUserRequest));
        CreateAppUserResponse createAppUserResponse = AppUserMapper.toCreateUsersResponse(appUser);
        createAppUserResponse.setStatus(Status.OK.getStatus());
        return createAppUserResponse;
    }

    @Override
    public AppUserDto getAppUserById(UUID appUserId) {
        return AppUserMapper.toDto(this.appUserRepository.findById(appUserId).orElseThrow(()->new NoSuchElementException(AppUserMessages.USER_NOT_FOUND)));
    }

    @Override
    public List<AppUserDto> getAllActiveAppUsers() {
        return AppUserMapper.toDtoList(this.appUserRepository.findAllByStatus(Boolean.TRUE));
    }

    @Override
    public ResponseEntity<AppUserDto> updateAppUserById(UUID appUserId, AppUserDto appUserDto) {
        if (!AppUserUtils.validateUsersRequest(appUserId, appUserDto.getAppUserId()))
            return ResponseEntity.badRequest().build();

        Optional<AppUser> existUsers = appUserRepository.findById(appUserId);
        if (existUsers.isEmpty())
            return ResponseEntity.notFound().build();

        final AppUserDto updateUsers = this.save(appUserDto);

        if (Objects.nonNull(updateUsers))
            return ResponseEntity.ok(updateUsers);


        return ResponseEntity.internalServerError().build();
    }

    @Override
    public AppUserDto updateAppUserNameById(UUID appUserId, String name) {
        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(()-> new NoSuchElementException(AppUserMessages.USER_NOT_FOUND));
        appUser.setName(name);
        return AppUserMapper.toDto(appUserRepository.save(appUser));
    }

    @Override
    public AppUserDto updateAppUserContactInfoById(UUID appUserId, UpdateAppUserContactInfoRequest request) {
        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(()-> new NoSuchElementException(AppUserMessages.USER_NOT_FOUND));
        appUser.setPhone(request.getPhone());
        appUser.setEmail(request.getEmail());
        appUserRepository.save(appUser);
        return AppUserMapper.toDto(appUser);
    }

    @Override
    public void deleteAppUserById(UUID appUserId) {
        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(()-> new NoSuchElementException(AppUserMessages.USER_NOT_FOUND));
        appUser.setStatus(false);
        appUserRepository.save(appUser);
    }

    private AppUserDto save(AppUserDto appUserDto){
        AppUser appUser = AppUserMapper.toEntity(appUserDto);
        appUser = appUserRepository.save(appUser);
        return AppUserMapper.toDto(appUser);
    }
}
