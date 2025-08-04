package com.errday.kafkahelper.adapter.in.web.dto;

public record ApiResponse<T>(
        boolean isSuccess,
        String message,
        T data) {

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> error(String message,  T data) {
        return new ApiResponse<>(false, message, data);
    }
}
