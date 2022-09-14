package com.azazafizer.server_v1.api.token.controller.exception;

public class EncryptException extends RuntimeException{
    public EncryptException(String message){
        super(message);
    }
}
