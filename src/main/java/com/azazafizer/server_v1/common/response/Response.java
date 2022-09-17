package com.azazafizer.server_v1.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response<T> {
    private final int status;
    private final String message;

    public Response(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
