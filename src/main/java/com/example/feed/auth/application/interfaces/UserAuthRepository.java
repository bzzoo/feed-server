package com.example.feed.auth.application.interfaces;

import com.example.feed.auth.domain.UserAuth;
import com.example.feed.user.domain.User;

public interface UserAuthRepository {
    UserAuth registerUser(UserAuth userAuth, User user);
    UserAuth loginUser(String email, String password, String fcmToken);
}