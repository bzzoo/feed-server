package com.example.feed.auth.application;

import com.example.feed.auth.application.dto.CreateUserAuthRequestDto;
import com.example.feed.auth.application.dto.LoginRequestDto;
import com.example.feed.auth.application.dto.UserAccessTokenResponseDto;
import com.example.feed.auth.application.interfaces.EmailVerificationRepository;
import com.example.feed.auth.application.interfaces.UserAuthRepository;
import com.example.feed.auth.domain.Email;
import com.example.feed.auth.domain.TokenProvider;
import com.example.feed.auth.domain.UserAuth;
import com.example.feed.user.domain.User;
import com.example.feed.user.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final EmailVerificationRepository emailVerificationRepository;
    private final UserAuthRepository userAuthRepository;

    public UserAccessTokenResponseDto registerUser(CreateUserAuthRequestDto dto) {
        Email email = Email.createEmail(dto.email());

        if (!emailVerificationRepository.isEmailVerified(email)) {
            throw new IllegalArgumentException();
        }

        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
        User user = new User(null,new UserInfo(dto.name(), dto.profileImageUrl()));
        userAuth = userAuthRepository.registerUser(userAuth, user);

        return createToken(userAuth);
    }

    public UserAccessTokenResponseDto loginUser(LoginRequestDto dto) {
        UserAuth userAuth = userAuthRepository.loginUser(dto.email(), dto.password(), dto.fcmToken());
        return createToken(userAuth);
    }

    private UserAccessTokenResponseDto createToken(UserAuth userAuth) {
        String token = tokenProvider.createToken(userAuth.getUserId(), userAuth.getRole());
        return new UserAccessTokenResponseDto(token);
    }
}