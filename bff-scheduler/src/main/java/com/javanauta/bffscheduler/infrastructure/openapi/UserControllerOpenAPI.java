package com.javanauta.bffscheduler.infrastructure.openapi;

import com.javanauta.bffscheduler.business.dto.request.LoginRequestDTO;
import com.javanauta.bffscheduler.business.dto.request.UserRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.LoginResponseDTO;
import com.javanauta.bffscheduler.business.dto.response.UserResponseDTO;
import com.javanauta.bffscheduler.core.config.OpenAPIConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User API", description = "API for managing users")
public interface UserControllerOpenAPI {

    @Operation(summary = "Find user by email", description = "Retrieve a user by the provided email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content)
            })
    @GetMapping("/{email}")
    ResponseEntity<UserResponseDTO> findUserByEmail(@PathVariable String email);

    @Operation(summary = "Create a new user", description = "Create a new user with the provided data",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data provided",
                            content = @Content)
            })
    @PostMapping
    ResponseEntity<UserResponseDTO> insert(@RequestBody UserRequestDTO request);

    @Operation(summary = "Update logged-in user's data", description = "Update the logged-in user's data based on the JWT token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User data updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content)
            })
    @SecurityRequirement(name = OpenAPIConfig.SECURITY_SCHEME)
    @PutMapping("/me")
    ResponseEntity<UserResponseDTO> updateCurrentUserData(@RequestBody UserRequestDTO request,
                                                          @RequestHeader("Authorization") @Parameter(hidden = true) String token);

    @Operation(summary = "Authenticate user", description = "Perform user login and return a JWT token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login successful",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponseDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid credentials",
                            content = @Content)
            })
    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto);

    @Operation(summary = "Delete user by email", description = "Delete the user with the provided email",
            responses = {
                    @ApiResponse(responseCode = "204", description = "User deleted successfully"),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content)
            })
    @SecurityRequirement(name = OpenAPIConfig.SECURITY_SCHEME)
    @DeleteMapping("/{email}")
    ResponseEntity<Void> deleteUser(@PathVariable String email,
                                    @RequestHeader(name = "Authorization") @Parameter(hidden = true) String token);
}