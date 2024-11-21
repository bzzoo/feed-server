package com.example.feed.post.repository.jpa;

import com.example.feed.post.repository.entity.LikeEntity;
import com.example.feed.post.repository.entity.LikeEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeEntityId> {

}
