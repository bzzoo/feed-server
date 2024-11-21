package com.example.feed.post.ui.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostContentResponseDto {

    private Long id;
    private String content;
    private Long userId;
    private String username;
    private String userProfileImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private boolean isLike;
    private Integer commentCount;
}
