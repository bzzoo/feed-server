package com.example.feed.post.domain;

import com.example.feed.common.domain.PositiveIntegerCounter;
import com.example.feed.post.domain.content.Content;
import com.example.feed.post.domain.content.PostPublicationStatus;
import com.example.feed.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationStatus status;

    public Post(Long id, User author, Content content, PostPublicationStatus status) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.status = status;
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeCount.increase();
    }

    public void unlike() {
        likeCount.decrease();
    }

    public void updatePost(User user, String updatedContent, PostPublicationStatus status) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.status = status;
        this.content.updateContent(updatedContent);
    }

    public int likeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }
}
