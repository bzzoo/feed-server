package com.example.feed.post.domain.comment;

import com.example.feed.common.domain.PositiveIntegerCounter;
import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.content.Content;
import com.example.feed.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public Comment(Long id, Post post, User author, Content content) {
        if (post == null || author == null || content == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
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

    public void updateComment(User user, String contentText) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.content.updateContent(contentText);
    }

    public int likeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }
}
