package com.example.reference.user.adapter.out.persistence;

import com.example.reference.user.application.port.out.CreateUserPort;
import com.example.reference.user.application.port.out.DeleteUserPort;
import com.example.reference.user.application.port.out.LoadUserPort;
import com.example.reference.user.application.port.out.UpdateUserPort;
import com.example.reference.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements CreateUserPort, LoadUserPort, UpdateUserPort, DeleteUserPort {
    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(User user) {
        return userMapper.userEntityToUser(
                userEntityRepository.save(userMapper.createUserToEntity(user))
        );
    }

    @Override
    public User loadUser(Long id) {
        return userEntityRepository.findById(id)
                .map(userMapper::userEntityToUser)
                .orElse(null);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userEntityRepository.findByEmail(email)
                .map(userMapper::userEntityToUser)
                .orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return userMapper.userEntityToUser(
                userEntityRepository.save(userMapper.updateUserToEntity(user))
        );
    }

    @Override
    public void deleteUser(Long id) {
        userEntityRepository.deleteById(id);
    }
}
