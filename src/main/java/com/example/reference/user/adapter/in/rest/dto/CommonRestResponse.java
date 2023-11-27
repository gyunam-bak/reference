package com.example.reference.user.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonRestResponse<T> {
    private T data;
    private String status;
    private String message;

    private CommonRestResponse(T data) {
        this.data = data;
    }

    private CommonRestResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static <T> CommonRestResponse<T> success(T data) {
        return new CommonRestResponse<>(data);
    }
    public static <T> CommonRestResponse<T> fail(String status, String message) {
        return new CommonRestResponse<>(status, message);
    }
}
