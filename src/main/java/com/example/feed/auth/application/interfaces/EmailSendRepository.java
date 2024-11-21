package com.example.feed.auth.application.interfaces;

import com.example.feed.auth.domain.Email;

public interface EmailSendRepository {

    void sendVerificationEmail(Email emailValue, String randomToken);
}
