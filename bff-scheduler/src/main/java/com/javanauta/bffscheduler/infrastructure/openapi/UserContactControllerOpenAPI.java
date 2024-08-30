package com.javanauta.bffscheduler.infrastructure.openapi;

import com.javanauta.bffscheduler.business.dto.request.ContactRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.ContactResponseDTO;
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

@Tag(name = "User Contact API", description = "API for managing contacts of the logged-in user")
@SecurityRequirement(name = OpenAPIConfig.SECURITY_SCHEME)
public interface UserContactControllerOpenAPI {

    @Operation(summary = "Find contacts of the logged-in user", description = "Retrieve the list of contacts for the logged-in user using a JWT token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Contacts retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ContactResponseDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content)
            })
    @GetMapping
    ResponseEntity<List<ContactResponseDTO>> findContacts(@RequestHeader("Authorization") @Parameter(hidden = true) String token);

    @Operation(summary = "Create a new contact", description = "Create a new contact for the logged-in user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Contact created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ContactResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data provided",
                            content = @Content),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content)
            })
    @PostMapping
    ResponseEntity<ContactResponseDTO> createContact(@RequestBody ContactRequestDTO request,
                                                     @RequestHeader("Authorization") @Parameter(hidden = true) String token);

    @Operation(summary = "Update an existing contact", description = "Update an existing contact for the logged-in user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Contact updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ContactResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data provided",
                            content = @Content),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Contact not found",
                            content = @Content)
            })
    @PutMapping("/{contactId}")
    ResponseEntity<ContactResponseDTO> updateContact(@RequestBody ContactRequestDTO request,
                                                     @PathVariable Long contactId,
                                                     @RequestHeader("Authorization") @Parameter(hidden = true) String token);

    @Operation(summary = "Delete a contact", description = "Delete a contact of the logged-in user by contact ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Contact deleted successfully"),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Contact not found",
                            content = @Content)
            })
    @DeleteMapping("/{contactId}")
    ResponseEntity<Void> deleteContact(@PathVariable Long contactId,
                                       @RequestHeader("Authorization") @Parameter(hidden = true) String token);
}
