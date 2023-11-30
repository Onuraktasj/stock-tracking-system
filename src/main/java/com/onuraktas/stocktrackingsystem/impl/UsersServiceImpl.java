package com.onuraktas.stocktrackingsystem.impl;

import com.onuraktas.stocktrackingsystem.dto.entity.UsersDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateUsersRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateUsersContactInfoRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateUsersResponse;
import com.onuraktas.stocktrackingsystem.entity.Users;
import com.onuraktas.stocktrackingsystem.entity.enums.Status;
import com.onuraktas.stocktrackingsystem.mapper.UsersMapper;
import com.onuraktas.stocktrackingsystem.repository.UsersRepository;
import com.onuraktas.stocktrackingsystem.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    @Override
    public CreateUsersResponse createUsers(CreateUsersRequest createUsersRequest) {
        Users users = this.usersRepository.save(UsersMapper.toEntity(createUsersRequest));
        CreateUsersResponse createUsersResponse = UsersMapper.toCreateUsersResponse(users);
        createUsersResponse.setStatus(Status.OK.getStatus());
        return createUsersResponse;
    }

    @Override
    public UsersDto getUsers(UUID usersId) {
        return UsersMapper.toDto(this.usersRepository.findById(usersId).orElseThrow(()->new NoSuchElementException("User Not Found")));
    }

    @Override
    public List<UsersDto> getAllUsers() {
        return UsersMapper.toDtoList(this.usersRepository.findAll());
    }

    @Override
    public ResponseEntity<UsersDto> updateUsers(UUID usersId, UsersDto usersDto) {
        if (Objects.isNull(usersId) || Objects.isNull(usersDto.getUsersId()) || !Objects.equals(usersId,usersDto.getUsersId()))
            return ResponseEntity.badRequest().build();

        Optional<Users> existUsers = usersRepository.findById(usersId);
        if (existUsers.isEmpty())
            return ResponseEntity.notFound().build();

        final UsersDto updateUsers = this.save(usersDto);

        if (Objects.nonNull(updateUsers))
            return ResponseEntity.ok(updateUsers);


        return ResponseEntity.internalServerError().build();
    }

    @Override
    public UsersDto updateUsersName(UUID usersId,String name) {
        Users users = usersRepository.findById(usersId).orElseThrow(()-> new NoSuchElementException("Users Not Found"));
        users.setName(name);
        return UsersMapper.toDto(usersRepository.save(users));
    }

    @Override
    public UsersDto updateUsersContactInfo(UUID usersId, UpdateUsersContactInfoRequest request) {
        Users users = usersRepository.findById(usersId).orElseThrow(()-> new NoSuchElementException("Users Not Found"));
        users.setPhone(request.getPhone());
        users.setEmail(request.getEmail());
        usersRepository.save(users);
        return UsersMapper.toDto(users);
    }

    @Override
    public void deleteUsers(UUID usersId) {
        Users users = usersRepository.findById(usersId).orElseThrow(()-> new NoSuchElementException("Users Not Found"));
        users.setStatus(false);
        usersRepository.save(users);
    }

    private UsersDto save(UsersDto usersDto){
        Users users = UsersMapper.toEntity(usersDto);
        users = usersRepository.save(users);
        return UsersMapper.toDto(users);

    }
}
