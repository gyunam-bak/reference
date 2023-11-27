package com.example.reference.user.adapter.in.rest.dto.request;

import com.example.reference.user.application.port.in.CreateUserCommand;
import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String email;
    private String password;
    private String name;
    private String phone;
}
