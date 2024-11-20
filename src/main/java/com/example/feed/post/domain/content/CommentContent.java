package com.example.feed.post.domain.content;

public class CommentContent extends Content {

    private final static int MIN_COMMENT_CONTENT_LENGTH = 5;
    private final static int MAX_COMMENT_CONTENT_LENGTH = 100;

    public CommentContent(String contentText) {
        super(contentText);
    }

    @Override
    protected void validateContent(String contentText) {
        if (contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (contentText.length() < MIN_COMMENT_CONTENT_LENGTH) {
            throw new IllegalArgumentException();
        }

        if (contentText.length() > MAX_COMMENT_CONTENT_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

}
