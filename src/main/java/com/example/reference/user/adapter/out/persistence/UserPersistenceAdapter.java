package com.example.reference.user.adapter.out.persistence;

import com.example.reference.user.application.port.out.CreateUserPort;
import com.example.reference.user.application.port.out.DeleteUserPort;
import com.example.reference.user.application.port.out.LoadUserPort;
import com.example.reference.user.application.port.out.UpdateUserPort;
import com.example.reference.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements CreateUserPort, LoadUserPort, UpdateUserPort, DeleteUserPort {
    private final UserEntityRepository userEntityRepository;
    private final Mapper mapper;

    @Override
    public User createUser(User user) {
        return mapper.userEntityToUser(
                userEntityRepository.save(mapper.createUserToEntity(user))
        );
    }

    @Override
    public User loadUser(Long id) {
        return userEntityRepository.findById(id)
                .map(mapper::userEntityToUser)
                .orElse(null);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userEntityRepository.findByEmail(email)
                .map(mapper::userEntityToUser)
                .orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return mapper.userEntityToUser(
                userEntityRepository.save(mapper.updateUserToEntity(user))
        );
    }

    @Override
    public void deleteUser(Long id) {
        userEntityRepository.deleteById(id);
    }
}
