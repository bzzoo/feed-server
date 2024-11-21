package com.example.feed.auth.application;


import com.example.feed.auth.application.interfaces.EmailSendRepository;
import com.example.feed.auth.application.interfaces.EmailVerificationRepository;
import com.example.feed.auth.domain.Email;
import com.example.feed.auth.domain.RandomTokenGenerator;
import com.example.feed.auth.application.dto.SendEmailRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailSendRepository emailSendRepository;

    public void sendEmail(SendEmailRequestDto dto) {
        Email emailValue = Email.createEmail(dto.email());
        String randomToken = RandomTokenGenerator.generateToken();

        emailVerificationRepository.createEmailVerification(emailValue, randomToken);
        emailSendRepository.sendVerificationEmail(emailValue, randomToken);
    }

    public void verify(String email, String token) {
        Email emailValue = Email.createEmail(email);
        emailVerificationRepository.verifyEmail(emailValue, token);
    }
}