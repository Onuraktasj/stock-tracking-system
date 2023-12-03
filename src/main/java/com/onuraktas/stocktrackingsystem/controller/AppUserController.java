package com.onuraktas.stocktrackingsystem.controller;

import com.onuraktas.stocktrackingsystem.dto.entity.AppUserDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateAppUserRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateAppUserContactInfoRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateAppUserResponse;
import com.onuraktas.stocktrackingsystem.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

        @PostMapping(value ="/crate")
    public ResponseEntity<CreateAppUserResponse> createAppUser(@RequestBody CreateAppUserRequest createAppUserRequest){
        return ResponseEntity.ok(appUserService.createAppUser(createAppUserRequest));
    }

        @GetMapping("/{appUserId}")
        public ResponseEntity<AppUserDto> getAppUserById(@PathVariable(value = "appUserId") UUID appUserId){
        return ResponseEntity.ok(appUserService.getAppUserById(appUserId));
        }

        @GetMapping(value = "/getAll")
    public ResponseEntity<List<AppUserDto>> getAllActiveAppUsers(){
        return ResponseEntity.ok(appUserService.getAllActiveAppUsers());
        }

        @PutMapping("/{appUserId}")
    public ResponseEntity<AppUserDto> updateAppUserById(@PathVariable(value = "appUserId") UUID appUserId, @RequestBody AppUserDto appUserDto){
        return appUserService.updateAppUserById(appUserId, appUserDto);
        }

        @PatchMapping("/{appUserId}")
    public ResponseEntity<AppUserDto> updateAppUserContactInfoById(@PathVariable(value = "appUserId") UUID appUserId, @RequestBody UpdateAppUserContactInfoRequest request){
        return ResponseEntity.ok(appUserService.updateAppUserContactInfoById(appUserId,request));
        }

        @DeleteMapping("/{appUserId}")
    public ResponseEntity<String> deleteAppUserById(@PathVariable(value = "appUserId") UUID appUserId){
        appUserService.deleteAppUserById(appUserId);
        return ResponseEntity.ok("Users Delete Successfully!");
    }
}
