package com.example.feed.auth.domain;

public class Email {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    private final String email;

    private Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public static Email createEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException();
        }
        return new Email(email);
    }

    private static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public String getEmailText() {
        return email;
    }
}
