package com.example.feed.post.application.dto;

public record CreateCommentRequestDto(
        Long userId,
        Long postId,
        String content
) {

}
