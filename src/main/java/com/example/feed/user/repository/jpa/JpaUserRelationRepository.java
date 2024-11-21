package com.example.feed.user.repository.jpa;

import com.example.feed.user.repository.entity.UserRelationEntity;
import com.example.feed.user.repository.entity.UserRelationIdEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRelationRepository extends
        JpaRepository<UserRelationEntity, UserRelationIdEntity> {

    @Query("SELECT u.followingUserId FROM UserRelationEntity u WHERE u.followerUserId = :userId")
    List<Long> findFollowers(Long userId);
}
