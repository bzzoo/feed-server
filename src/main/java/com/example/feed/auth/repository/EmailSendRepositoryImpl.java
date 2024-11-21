package com.example.feed.auth.repository;

import com.example.feed.auth.application.interfaces.EmailSendRepository;
import com.example.feed.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendVerificationEmail(Email email, String token) {
        // send email
    }
}