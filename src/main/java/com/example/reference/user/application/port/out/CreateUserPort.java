package com.example.reference.user.application.port.out;

import com.example.reference.user.domain.User;

public interface CreateUserPort {
    User createUser(User user);
}
