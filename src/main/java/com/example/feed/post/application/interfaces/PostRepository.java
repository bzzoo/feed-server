package com.example.feed.post.application.interfaces;

import com.example.feed.post.domain.Post;

public interface PostRepository {

    Post findById(Long id);

    Post save(Post post);
}
