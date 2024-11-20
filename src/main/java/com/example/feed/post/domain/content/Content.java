package com.example.feed.post.domain.content;

import com.example.feed.common.domain.DateTimeInfo;

public abstract class Content {

    String contentText;
    final DateTimeInfo dateTimeInfo;

    protected Content(String contentText) {
        validateContent(contentText);
        this.contentText = contentText;
        this.dateTimeInfo = new DateTimeInfo();
    }

    public void updateContent(String contentText) {
        validateContent(contentText);
        this.contentText = contentText;
        this.dateTimeInfo.updateDateTime();
    }

    protected abstract void validateContent(String contentText);

    public String getContentText() {
        return contentText;
    }
}
