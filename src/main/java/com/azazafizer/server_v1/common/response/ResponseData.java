package com.azazafizer.server_v1.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseData<T> extends Response<T> {
    private final T data;

    public ResponseData(HttpStatus status, String message, T data) {
        super(status, message);
        this.data = data;
    }
}
