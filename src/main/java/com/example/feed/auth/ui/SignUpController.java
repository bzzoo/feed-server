package com.example.feed.auth.ui;

import com.example.feed.auth.application.AuthService;
import com.example.feed.auth.application.EmailService;
import com.example.feed.auth.application.dto.CreateUserAuthRequestDto;
import com.example.feed.auth.application.dto.SendEmailRequestDto;
import com.example.feed.auth.application.dto.UserAccessTokenResponseDto;
import com.example.feed.common.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto) {
        emailService.sendEmail(dto);
        return Response.ok(null);
    }

    @GetMapping("/verify-email")
    public Response<Void> verifyEmail(String email, String token) {
        emailService.verify(email, token);
        return Response.ok(null);
    }

    @PostMapping("/register")
    public Response<UserAccessTokenResponseDto> register(@RequestBody CreateUserAuthRequestDto dto) {
        return Response.ok(authService.registerUser(dto));
    }
}
