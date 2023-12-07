package com.example.reference.user.adapter.in.rest.dto.response;

import com.example.reference.user.domain.User;
import lombok.Getter;

@Getter
public class UserDto {
    private final String email;
    private final String name;
    private final String phone;

    private UserDto(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public static UserDto fromUser(User user) {
        return new UserDto(user.getEmail(), user.getName(), user.getPhone());
    }
}
