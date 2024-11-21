package com.example.feed.post.application;

import com.example.feed.post.application.dto.CreateCommentRequestDto;
import com.example.feed.post.application.dto.LikeRequestDto;
import com.example.feed.post.application.dto.UpdateCommentRequestDto;
import com.example.feed.post.application.interfaces.CommentRepository;
import com.example.feed.post.application.interfaces.LikeRepository;
import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.comment.Comment;
import com.example.feed.post.domain.content.CommentContent;
import com.example.feed.post.domain.content.Content;
import com.example.feed.user.application.UserService;
import com.example.feed.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public CommentService(UserService userService, PostService postService,
            CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Post post = postService.getPost(dto.postId());
        Content content = new CommentContent(dto.content());
        Comment comment = new Comment(null, post, author, content);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, UpdateCommentRequestDto dto) {
        Comment comment = getComment(commentId);
        User author = userService.getUser(dto.userId());

        comment.updateComment(author, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            throw new IllegalArgumentException();
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            comment.unlike();
            likeRepository.unlike(comment, user);
        }
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId);
    }
}
