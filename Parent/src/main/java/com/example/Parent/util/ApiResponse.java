package com.example.Parent.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * this class will be used by all the REST APIs
 * to be used as a response throughout the
 * microservice architecture
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private int status;
    private String message;
    private LocalDateTime createdAt;
    private T data;

    ApiResponse(int status,String message, T data)
    {
        this.status = status;
        this.message = message;
        this.data = data;
        this.createdAt = LocalDateTime.now();
    }
    public static <T> ApiResponse<T> onSuccess(T data, String message)
    {
        return new ApiResponse<>(200,message,data);
    }

    public static <T> ApiResponse<T> onError(String message)
    {
        return new ApiResponse<>(200,message,null);
    }
}
