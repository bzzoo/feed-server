package com.example.feed.post.repository.post_queue;

import com.example.feed.post.ui.dto.GetPostContentResponseDto;
import java.util.List;

public interface UserPostQueueQueryRepository {

    List<GetPostContentResponseDto> getPostList(Long userId, Long lastPostId);

}
