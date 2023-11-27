package com.example.reference.user.application.port.in;

import com.example.reference.user.adapter.in.rest.dto.request.UpdateUserRequest;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateUserCommand {
    private long id;
    private String password;
    private String name;
    private String phone;

    public UpdateUserCommand(long id, UpdateUserRequest request) {
        this.id = id;
        this.password = request.getPassword();
        this.name = request.getName();
        this.phone = request.getPhone();
    }

    public static UpdateUserCommand fromRequest(long id, UpdateUserRequest request) {
        return new UpdateUserCommand(id, request);
    }
}
