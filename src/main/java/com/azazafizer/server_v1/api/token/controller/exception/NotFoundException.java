package com.azazafizer.server_v1.api.token.controller.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
