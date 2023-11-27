package com.example.reference.user.application.port.in;

import com.example.reference.user.domain.User;

public interface CreateUserUseCase {
    User createUser(CreateUserCommand command);
}
