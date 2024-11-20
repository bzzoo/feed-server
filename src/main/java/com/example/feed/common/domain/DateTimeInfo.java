package com.example.feed.common.domain;

import java.time.LocalDateTime;

public class DateTimeInfo {

    private boolean isEdited;
    private LocalDateTime dateTime;

    public DateTimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }

    public DateTimeInfo(boolean isEdited, LocalDateTime dateTime) {
        this.isEdited = isEdited;
        this.dateTime = dateTime;
    }

    public void updateDateTime() {
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
