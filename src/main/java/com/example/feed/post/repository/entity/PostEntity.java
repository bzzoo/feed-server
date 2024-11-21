package com.example.feed.post.repository.entity;

import com.example.feed.common.domain.PositiveIntegerCounter;
import com.example.feed.common.repository.entity.TimeBaseEntity;
import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.content.PostContent;
import com.example.feed.post.domain.content.PostPublicationStatus;
import com.example.feed.user.repository.entity.UserEntity;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "posts")
@Entity
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    @Convert(converter = PostPublicationStatus.class)
    private PostPublicationStatus status;

    private String content;
    private Integer likeCount;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.status = post.getStatus();
        this.content = post.getContent();
        this.likeCount = post.likeCount();
    }

    public Post toDomain() {
        return Post.builder()
                .id(id)
                .author(author.toDomain())
                .status(status)
                .content(new PostContent(content))
                .likeCount(new PositiveIntegerCounter(likeCount))
                .build();
    }

}
