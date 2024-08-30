package com.javanauta.bffscheduler.infrastructure.openapi;

import com.javanauta.bffscheduler.business.dto.request.AddressRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.AddressResponseDTO;
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

import java.util.List;

@Tag(name = "User Address API", description = "API for managing addresses of the logged-in user")
@SecurityRequirement(name = OpenAPIConfig.SECURITY_SCHEME)
public interface UserAddressControllerOpenAPI {

    @Operation(summary = "Find addresses of the logged-in user", description = "Retrieve the list of addresses for the logged-in user using a JWT token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressResponseDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content)
            })
    @GetMapping
    ResponseEntity<List<AddressResponseDTO>> findAddresses(@RequestHeader("Authorization") @Parameter(hidden = true) String token);

    @Operation(summary = "Create a new address", description = "Create a new address for the logged-in user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Address created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data provided",
                            content = @Content),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content)
            })
    @PostMapping
    ResponseEntity<AddressResponseDTO> createAddress(@RequestBody AddressRequestDTO request,
                                                     @RequestHeader("Authorization") @Parameter(hidden = true) String token);

    @Operation(summary = "Update an existing address", description = "Update an existing address for the logged-in user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Address updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data provided",
                            content = @Content),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Address not found",
                            content = @Content)
            })
    @PutMapping("/{addressId}")
    ResponseEntity<AddressResponseDTO> updateAddress(@RequestBody AddressRequestDTO request,
                                                     @PathVariable Long addressId,
                                                     @RequestHeader("Authorization") @Parameter(hidden = true) String token);

    @Operation(summary = "Delete an address", description = "Delete an address of the logged-in user by address ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Address not found",
                            content = @Content)
            })
    @DeleteMapping("/{addressId}")
    ResponseEntity<Void> deleteAddress(@PathVariable Long addressId,
                                       @RequestHeader("Authorization") @Parameter(hidden = true) String token);
}
