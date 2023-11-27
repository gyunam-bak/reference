package com.example.reference.user.application;

import com.example.reference.user.application.port.in.*;
import com.example.reference.user.application.port.out.CreateUserPort;
import com.example.reference.user.application.port.out.DeleteUserPort;
import com.example.reference.user.application.port.out.LoadUserPort;
import com.example.reference.user.application.port.out.UpdateUserPort;
import com.example.reference.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements CreateUserUseCase, UpdateUserUseCase, DeleteUserUseCase {
    private final CreateUserPort createUserPort;
    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;
    private final DeleteUserPort deleteUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(CreateUserCommand command) {
        if (loadUserPort.loadUserByEmail(command.getEmail()) != null) {
            throw new DataIntegrityViolationException("Duplicate email address.");
        }

        return createUserPort.createUser(
                User.builder()
                        .email(command.getEmail())
                        .password(passwordEncoder.encode(command.getPassword()))
                        .name(command.getName())
                        .phone(command.getPhone())
                        .build()
        );
    }

    @Override
    public User updateUser(UpdateUserCommand command) {
        User user = loadUserPort.loadUser(command.getId());

        if (user == null) {
            throw new EntityNotFoundException("User not found.");
        }

        user.setPassword(passwordEncoder.encode(command.getPassword()));
        user.setName(command.getName());
        user.setPhone(command.getPhone());

        return updateUserPort.updateUser(user);
    };

    @Override
    public void deleteUser(Long id) {
        deleteUserPort.deleteUser(id);
    };
}
