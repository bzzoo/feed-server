package com.example.feed.post.repository;

import com.example.feed.post.application.interfaces.PostRepository;
import com.example.feed.post.domain.Post;
import com.example.feed.post.repository.entity.PostEntity;
import com.example.feed.post.repository.jpa.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        PostEntity entity = new PostEntity(post);
        if(post.getId() != null) {
            jpaPostRepository.updatePostEntity(entity);
            return entity.toDomain();
        }
        entity = jpaPostRepository.save(entity);
        return entity.toDomain();
    }

    @Override
    public Post findById(Long id) {
        PostEntity entity = jpaPostRepository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return entity.toDomain();
    }
}
