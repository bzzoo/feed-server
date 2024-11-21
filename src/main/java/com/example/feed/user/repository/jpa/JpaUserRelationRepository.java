package com.example.feed.user.repository.jpa;

import com.example.feed.user.repository.entity.UserRelationEntity;
import com.example.feed.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends
        JpaRepository<UserRelationEntity, UserRelationIdEntity> {

}
