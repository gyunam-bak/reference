package com.example.reference.user.adapter.in.rest.dto.request;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private String password;
    private String name;
    private String phone;
}
