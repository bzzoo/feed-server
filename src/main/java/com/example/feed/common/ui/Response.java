package com.example.feed.common.ui;


public record Response<T>(
        Integer code,
        String message,
        T data
) {

    public static <T> Response<T> ok(T data) {
        return new Response<>(200, "OK", data);
    }

    public static <T> Response<T> error(ErrorCode error) {
        return new Response<>(error.getCode(), error.getMessage(), null);
    }
}
