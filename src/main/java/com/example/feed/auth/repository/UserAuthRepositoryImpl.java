package com.example.feed.auth.repository;

import com.example.feed.auth.application.interfaces.UserAuthRepository;
import com.example.feed.auth.domain.UserAuth;
import com.example.feed.auth.repository.entity.UserAuthEntity;
import com.example.feed.auth.repository.jpa.JpaUserAuthRepository;
import com.example.feed.user.application.interfaces.UserRepository;
import com.example.feed.user.domain.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserAuth registerUser(UserAuth userAuth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(userAuth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);
        return userAuthEntity.toUserAuth();
    }

    @Override
    @Transactional
    public UserAuth loginUser(String email, String password, String fcmToken) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findByEmail(email).orElseThrow();

        UserAuth userAuth = userAuthEntity.toUserAuth();
        if (!userAuth.matchPassword(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        userAuthEntity.updateLastLoginAt();
        //
        return userAuth;
    }
}