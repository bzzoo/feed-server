package com.example.feed.post.repository.entity;

import com.example.feed.common.repository.entity.TimeBaseEntity;
import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.comment.Comment;
import com.example.feed.user.domain.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "likes")
@Entity
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeEntityId id;

    public LikeEntity(Post post, User user) {
        this.id = new LikeEntityId(post.getId(), user.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User user) {
        this.id = new LikeEntityId(comment.getId(), user.getId(), LikeTarget.COMMENT.name());
    }
}
