package com.example.feed.auth.application.dto;

import lombok.Getter;

@Getter
public record SendEmailRequestDto(
        String email
) {

    public SendEmailRequestDto(String email) {
        this.email = email;
    }

}
