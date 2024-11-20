package com.example.feed.user.application;

import com.example.feed.user.application.dto.CreateUserRequestDto;
import com.example.feed.user.application.interfaces.UserRepository;
import com.example.feed.user.domain.User;
import com.example.feed.user.domain.UserInfo;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserRequestDto dto) {
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info);
        userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(IllegalAccessError::new);
    }
}
