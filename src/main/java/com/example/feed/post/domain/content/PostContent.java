package com.example.feed.post.domain.content;

public class PostContent extends Content {

    private static final int MIN_POST_CONTENT_LENGTH = 5;
    private static final int MAX_POST_CONTENT_LENGTH = 500;

    public PostContent(String contentText) {
        super(contentText);
    }

    @Override
    protected void validateContent(String contentText) {
        if(contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (contentText.length() < MIN_POST_CONTENT_LENGTH) {
            throw new IllegalArgumentException();
        }

        if (contentText.length() > MAX_POST_CONTENT_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}
