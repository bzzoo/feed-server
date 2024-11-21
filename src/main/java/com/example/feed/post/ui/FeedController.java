package com.example.feed.post.ui;

import com.example.feed.common.ui.Response;
import com.example.feed.post.repository.post_queue.UserPostQueueQueryRepository;
import com.example.feed.post.ui.dto.GetPostContentResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class FeedController {

    private final UserPostQueueQueryRepository queueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(
            @PathVariable(name = "userId") Long userId,
            Long lastPostId
    ) {
        List<GetPostContentResponseDto> postList = queueQueryRepository.getPostList(userId, lastPostId);
        return Response.ok(postList);
    }
}
