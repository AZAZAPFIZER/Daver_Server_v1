package com.azazafizer.server_v1.api.token.controller.exception;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message){
        super(message);
    }
}
