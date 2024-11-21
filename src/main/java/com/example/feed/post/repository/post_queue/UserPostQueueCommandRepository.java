package com.example.feed.post.repository.post_queue;

import com.example.feed.post.repository.entity.PostEntity;

public interface UserPostQueueCommandRepository {

    void publishPost(PostEntity postEntity);

    void save(Long userId, Long targetId);

    void deleteUnfollowPost(Long userId, Long targetId);
}
