package com.example.feed.post.ui;

import com.example.feed.common.ui.Response;
import com.example.feed.post.application.CommentService;
import com.example.feed.post.application.dto.CreateCommentRequestDto;
import com.example.feed.post.application.dto.LikeRequestDto;
import com.example.feed.post.application.dto.UpdateCommentRequestDto;
import com.example.feed.post.application.dto.UpdatePostRequestDto;
import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.comment.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    public Response<Long> creatComment(@RequestBody CreateCommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updatePost(
            @PathVariable("commentId") Long commentId,
            @RequestBody UpdateCommentRequestDto dto) {
        Comment comment = commentService.updateComment(commentId, dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikeRequestDto dto) {
        commentService.likeComment(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikePost(@RequestBody LikeRequestDto dto) {
        commentService.unlikeComment(dto);
        return Response.ok(null);
    }
}
