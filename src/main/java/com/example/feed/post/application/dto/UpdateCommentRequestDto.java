package com.example.feed.post.application.dto;

public record UpdateCommentRequestDto(
        Long userId,
        Long postId,
        String content
) {

}
