package com.example.reference.user.application.port.in;

import com.example.reference.user.adapter.in.rest.dto.request.CreateUserRequest;
import lombok.Getter;

@Getter
public class CreateUserCommand {
    private String email;
    private String password;
    private String name;
    private String phone;

    public CreateUserCommand(CreateUserRequest request) {
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.name = request.getName();
        this.phone = request.getPhone();
    }

    public static CreateUserCommand fromRequest(CreateUserRequest request) {
        return new CreateUserCommand(request);
    }
}
