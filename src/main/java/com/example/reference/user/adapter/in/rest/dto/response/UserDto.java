package com.example.reference.user.adapter.in.rest.dto.response;

import com.example.reference.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private final String email;
    private final String name;
    private final String phone;

    public static UserDto fromUser(User user) {
        return new UserDto(user.getEmail(), user.getName(), user.getPhone());
    }
}
