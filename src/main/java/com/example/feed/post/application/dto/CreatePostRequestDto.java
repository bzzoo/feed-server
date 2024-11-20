package com.example.feed.post.application.dto;

import com.example.feed.post.domain.content.PostPublicationStatus;

public record CreatePostRequestDto(
        Long userId,
        String content,
        PostPublicationStatus status
) {

}
