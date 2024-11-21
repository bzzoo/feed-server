package com.example.feed.user.repository.entity;

import com.example.feed.common.repository.entity.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_relations")
@IdClass(UserRelationIdEntity.class)
@Entity
public class UserRelationEntity extends TimeBaseEntity {

    @Id
    private Long followerUserId;

    @Id
    private Long followingUserId;

    public UserRelationEntity(Long followerUserId, Long followingUserId) {
        this.followerUserId = followerUserId;
        this.followingUserId = followingUserId;
    }
}
