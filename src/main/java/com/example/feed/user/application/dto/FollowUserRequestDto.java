package com.example.feed.user.application.dto;

public record FollowUserRequestDto(
        Long userId,
        Long targetUserId
) {

}
