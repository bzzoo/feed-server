package com.example.feed.post.repository;

import com.example.feed.post.application.interfaces.CommentRepository;
import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.comment.Comment;
import com.example.feed.post.repository.entity.CommentEntity;
import com.example.feed.post.repository.jpa.JpaCommentRepository;
import com.example.feed.post.repository.jpa.JpaPostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        Post targetPost = comment.getPost();
        CommentEntity entity = new CommentEntity(comment);
        if (comment.getId() != null) {
            jpaCommentRepository.updateCommentEntity(entity);
            return entity.toDomain();
        }
        jpaPostRepository.incrementCommentCount(targetPost.getId());
        return jpaCommentRepository.save(entity).toDomain();
    }

    @Override
    public Comment findById(Long id) {
        return jpaCommentRepository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .toDomain();
    }
}
