package com.azazafizer.server_v1.api.token.controller.exception;

public class InternalServerException extends RuntimeException{
    public InternalServerException(String message){
        super(message);
    }
}
