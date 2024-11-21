package com.example.feed.post.repository.entity;

import com.example.feed.post.domain.content.PostPublicationStatus;
import jakarta.persistence.AttributeConverter;

public class PostPublicationStatusConverter implements
        AttributeConverter<PostPublicationStatus, String> {

    @Override
    public String convertToDatabaseColumn(PostPublicationStatus attribute) {
        return attribute.name();
    }

    @Override
    public PostPublicationStatus convertToEntityAttribute(String dbData) {
        return PostPublicationStatus.valueOf(dbData);
    }

}
