package com.javanauta.user.controllers;

import com.javanauta.user.business.dto.request.LoginRequestDTO;
import com.javanauta.user.business.dto.request.UserRequestDTO;
import com.javanauta.user.business.dto.response.LoginResponseDTO;
import com.javanauta.user.business.dto.response.UserResponseDTO;
import com.javanauta.user.business.usecases.CreateUserService;
import com.javanauta.user.business.usecases.DeleteUserByEmailService;
import com.javanauta.user.business.usecases.FindUserByEmailService;
import com.javanauta.user.business.usecases.UpdateCurrentUserService;
import com.javanauta.user.core.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final FindUserByEmailService findUserByEmailService;
    private final CreateUserService createUserService;
    private final UpdateCurrentUserService updateCurrentUserService;
    private final DeleteUserByEmailService deleteUserByEmailService;;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

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
    public ResponseEntity<UserResponseDTO> updateCurrentUserData(@RequestBody UserRequestDTO request) {
        UserResponseDTO response = this.updateCurrentUserService.execute(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok().body(new LoginResponseDTO(token));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        this.deleteUserByEmailService.execute(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
