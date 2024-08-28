package com.javanauta.user.controllers;

import com.javanauta.user.business.UserService;
import com.javanauta.user.business.dto.request.UserRequestDTO;
import com.javanauta.user.business.dto.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody UserRequestDTO request) {
        UserResponseDTO created = this.userService.save(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}")
                .buildAndExpand(created.getEmail()).toUri();
        return ResponseEntity.created(uri).body(created);
    }
}
