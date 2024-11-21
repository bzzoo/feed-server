package com.example.feed.auth.ui;

import com.example.feed.auth.application.AuthService;
import com.example.feed.auth.application.dto.LoginRequestDto;
import com.example.feed.auth.application.dto.UserAccessTokenResponseDto;
import com.example.feed.common.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/login")
@RestController
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(@RequestBody LoginRequestDto dto) {
        return Response.ok(authService.loginUser(dto));
    }
}