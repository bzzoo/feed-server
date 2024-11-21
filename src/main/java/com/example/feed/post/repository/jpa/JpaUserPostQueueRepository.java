package com.example.feed.post.repository.jpa;

import com.example.feed.post.repository.entity.UserPostQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserPostQueueRepository extends JpaRepository<UserPostQueueEntity, Long> {

    void deleteAllByUserIdAndAuthorId(Long userId, Long targetId);
}
