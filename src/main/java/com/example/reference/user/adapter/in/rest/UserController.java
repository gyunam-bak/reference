package com.example.reference.user.adapter.in.rest;

import com.example.reference.user.adapter.in.rest.dto.CommonRestResponse;
import com.example.reference.user.adapter.in.rest.dto.request.CreateUserRequest;
import com.example.reference.user.adapter.in.rest.dto.request.UpdateUserRequest;
import com.example.reference.user.adapter.in.rest.dto.response.UserDto;
import com.example.reference.user.application.port.in.*;
import com.example.reference.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping
    public ResponseEntity<CommonRestResponse<UserDto>> createUser(
            @RequestBody CreateUserRequest request
            ) {
        try {
            User createdUser = createUserUseCase.createUser(CreateUserCommand.fromRequest(request));

            return ResponseEntity.ok().body(
                    CommonRestResponse.success(UserDto.fromUser(createdUser))
            );
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(500).body(CommonRestResponse.fail("500", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonRestResponse<UserDto>> updateUser(
            @PathVariable long id,
            @RequestBody UpdateUserRequest request
    ) {
        try {
            User udpatedUser = updateUserUseCase.updateUser(UpdateUserCommand.fromRequest(id, request));

            return ResponseEntity.ok().body(
                    CommonRestResponse.success(UserDto.fromUser(udpatedUser))
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(CommonRestResponse.fail("404", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        deleteUserUseCase.deleteUser(id);
        return ResponseEntity.ok("User successfully removed.");
    }
}
