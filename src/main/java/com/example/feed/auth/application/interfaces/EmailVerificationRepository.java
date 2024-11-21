package com.example.feed.auth.application.interfaces;

import com.example.feed.auth.domain.Email;

public interface EmailVerificationRepository {
    void createEmailVerification(Email email, String token);

    void verifyEmail(Email email, String token);

    boolean isEmailVerified(Email email);
}