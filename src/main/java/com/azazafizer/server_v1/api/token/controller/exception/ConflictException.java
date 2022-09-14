package com.azazafizer.server_v1.api.token.controller.exception;

public class ConflictException extends RuntimeException{
    public ConflictException(String message){
        super(message);
    }
}
