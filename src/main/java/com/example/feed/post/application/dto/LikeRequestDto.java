package com.example.feed.post.application.dto;

public record LikeRequestDto(
        Long userId,
        Long targetId
) {

}
