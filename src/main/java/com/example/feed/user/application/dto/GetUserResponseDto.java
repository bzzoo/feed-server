package com.example.feed.user.application.dto;

import com.example.feed.user.domain.User;

public record GetUserResponseDto(
        Long id,
        String name,
        String profileImage,
        Integer followerCount,
        Integer followingCount
) {

    public GetUserResponseDto(User user) {
        this(user.getId(), user.getName(), user.getProfileImage(), user.followerCount(), user.followingCount());
    }
}
