package com.example.feed.post.application.interfaces;

import com.example.feed.post.domain.Post;
import java.util.Optional;

public interface PostRepository {

    Optional<Post> findById(Long id);

    Post save(Post post);
}
