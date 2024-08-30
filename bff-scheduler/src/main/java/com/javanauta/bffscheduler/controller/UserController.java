package com.javanauta.bffscheduler.controller;


import com.javanauta.bffscheduler.business.dto.request.LoginRequestDTO;
import com.javanauta.bffscheduler.business.dto.request.UserRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.LoginResponseDTO;
import com.javanauta.bffscheduler.business.dto.response.UserResponseDTO;
import com.javanauta.bffscheduler.business.usecases.*;
import com.javanauta.bffscheduler.infrastructure.openapi.UserControllerOpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements UserControllerOpenAPI {

    private final FindUserByEmailService findUserByEmailService;
    private final CreateUserService createUserService;
    private final UpdateCurrentUserService updateCurrentUserService;
    private final DeleteUserByEmailService deleteUserByEmailService;
    private final LoginService loginService;

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> findUserByEmail(@PathVariable String email) {
        UserResponseDTO user = findUserByEmailService.execute(email);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody UserRequestDTO request) {
        UserResponseDTO created = this.createUserService.execute(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}")
                .buildAndExpand(created.getEmail()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateCurrentUserData(@RequestBody UserRequestDTO request,
                                                                 @RequestHeader("Authorization") String token) {
        UserResponseDTO response = this.updateCurrentUserService.execute(request, token);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.loginService.execute(dto));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email,
                                           @RequestHeader("Authorization") String token
    ) {
        this.deleteUserByEmailService.execute(email, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
