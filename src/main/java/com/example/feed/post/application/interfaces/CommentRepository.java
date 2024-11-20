package com.example.feed.post.application.interfaces;

import com.example.feed.post.domain.comment.Comment;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(Long id);
}
