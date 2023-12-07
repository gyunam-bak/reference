package com.example.reference.user.application.port.in;

import com.example.reference.user.adapter.in.rest.dto.request.UpdateUserRequest;
import lombok.Getter;

@Getter
public class UpdateUserCommand {
    private final long id;
    private final String password;
    private final String name;
    private final String phone;

    private UpdateUserCommand(long id, UpdateUserRequest request) {
        this.id = id;
        this.password = request.getPassword();
        this.name = request.getName();
        this.phone = request.getPhone();
    }

    public static UpdateUserCommand fromRequest(long id, UpdateUserRequest request) {
        return new UpdateUserCommand(id, request);
    }
}
