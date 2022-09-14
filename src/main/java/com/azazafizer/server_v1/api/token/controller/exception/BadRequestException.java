package com.azazafizer.server_v1.api.token.controller.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message){
        super(message);
    }
}
