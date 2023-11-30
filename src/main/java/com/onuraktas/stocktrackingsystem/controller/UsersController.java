package com.onuraktas.stocktrackingsystem.controller;

import com.onuraktas.stocktrackingsystem.dto.entity.UsersDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateUsersRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateUsersContactInfoRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateUsersResponse;
import com.onuraktas.stocktrackingsystem.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController (UsersService usersService){
        this.usersService = usersService;
    }

        @PostMapping(value ="/crate")
    public ResponseEntity<CreateUsersResponse> createUsers(@RequestBody CreateUsersRequest createUsersRequest){
        return ResponseEntity.ok(usersService.createUsers(createUsersRequest));
    }

        @GetMapping("/{usersId}")
        public ResponseEntity<UsersDto> getUsers(@PathVariable(value = "usersId") UUID usersId){
        return ResponseEntity.ok(usersService.getUsers(usersId));
        }

        @GetMapping(value = "/getAll")
    public ResponseEntity<List<UsersDto>> getAllUsers(){
        return ResponseEntity.ok(usersService.getAllUsers());
        }

        @PutMapping("/{usersId}")
    public ResponseEntity<UsersDto> updateUsers (@PathVariable(value = "usersId") UUID usersId,@RequestBody UsersDto usersDto){
        return usersService.updateUsers(usersId,usersDto);
        }

        @PatchMapping("/{usersId}")
    public ResponseEntity<UsersDto> updateUsersContactInfo(@PathVariable(value = "usersId") UUID userId, @RequestBody UpdateUsersContactInfoRequest request){
        return ResponseEntity.ok(usersService.updateUsersContactInfo(userId,request));
        }

        @DeleteMapping("/{usersId}")
    public ResponseEntity<String> deleteUsers(@PathVariable(value = "usersId") UUID usersId){
        usersService.deleteUsers(usersId);
        return ResponseEntity.ok("Users Delete Successfully!");
    }
}
