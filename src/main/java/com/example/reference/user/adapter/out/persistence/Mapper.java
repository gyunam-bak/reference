package com.example.reference.user.adapter.out.persistence;

import com.example.reference.user.domain.User;

public interface Mapper {
    UserEntity createUserToEntity (User user);
    UserEntity updateUserToEntity (User user);
    User userEntityToUser(UserEntity user);
}
