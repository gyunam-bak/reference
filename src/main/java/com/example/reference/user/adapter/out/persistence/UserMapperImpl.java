package com.example.reference.user.adapter.out.persistence;

import com.example.reference.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserEntity createUserToEntity (User user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public UserEntity updateUserToEntity (User user) {
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public User userEntityToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .phone(userEntity.getPhone())
                .build();
    }
}
