package com.example.feed.user.domain;

public class UserInfo {

    private final String name;
    private final String profileUrl;

    public UserInfo(String name, String profileUrl) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.profileUrl = profileUrl;
    }

    public String getName() {
        return name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}
