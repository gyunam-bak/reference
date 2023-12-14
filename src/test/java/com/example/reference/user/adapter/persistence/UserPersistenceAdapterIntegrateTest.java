package com.example.reference.user.adapter.persistence;

import com.example.reference.user.adapter.out.persistence.*;
import com.example.reference.user.domain.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({UserPersistenceAdapter.class, UserMapperImpl.class})
public class UserPersistenceAdapterIntegrateTest {

    @Autowired
    private UserPersistenceAdapter userPersistenceAdapter;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserEntityRepository userEntityRepository;

    private final Faker faker = new Faker();

    @Test
    void createUser() {
        // given
        User fakerUser = User.builder()
                .email(faker.internet().emailAddress())
                .build();
        // when
        User createdUser = userPersistenceAdapter.createUser(fakerUser);
        //then
        User user = userEntityRepository.findById(createdUser.getId())
                .map(userMapper::userEntityToUser)
                .orElse(null);
        assertNotNull(user);
    }

    @Test
    void loadUser() {
        // given
        Long id = userEntityRepository.save(newFakerUserEntity()).getId();
        // when
        User loadedUser = userPersistenceAdapter.loadUser(id);
        // then
        assertNotNull(loadedUser);
    }

    @Test
    void loadUserByEmail() {
        // given
        String email = userEntityRepository.save(newFakerUserEntity()).getEmail();
        // when
        User loadedUser = userPersistenceAdapter.loadUserByEmail(email);
        // then
        assertNotNull(loadedUser);
    }

    @Test
    void updateUser() {
        // given
        User savedUser = userMapper.userEntityToUser(userEntityRepository.save(newFakerUserEntity()));
        Long id = savedUser.getId();
        // when
        savedUser.setPassword(faker.internet().password());
        savedUser.setName(faker.name().fullName());
        savedUser.setPhone(faker.phoneNumber().cellPhone());
        userPersistenceAdapter.updateUser(savedUser);
        // then
        User user = userEntityRepository.findById(id).map(userMapper::userEntityToUser).orElse(null);
        assertThat(savedUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    void deleteUser() {
        // given
        Long id = userMapper.userEntityToUser(userEntityRepository.save(newFakerUserEntity())).getId();
        // when
        userPersistenceAdapter.deleteUser(id);
        // then
        User user = userEntityRepository.findById(id).map(userMapper::userEntityToUser).orElse(null);
        assertNull(user);
    }

    private UserEntity newFakerUserEntity() {
        return UserEntity.builder()
                .email(faker.internet().emailAddress())
                .build();
    }
}
