package com.example.reference.user.application.port.in;

import com.example.reference.user.domain.User;

public interface UpdateUserUseCase {
    User updateUser(UpdateUserCommand command);
}
