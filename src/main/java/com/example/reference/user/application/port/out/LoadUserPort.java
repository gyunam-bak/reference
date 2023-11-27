package com.example.reference.user.application.port.out;

import com.example.reference.user.domain.User;

public interface LoadUserPort {
    User loadUser(Long id);
    User loadUserByEmail(String email);
}
