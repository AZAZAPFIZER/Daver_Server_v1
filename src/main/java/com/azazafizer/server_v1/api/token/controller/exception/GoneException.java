package com.azazafizer.server_v1.api.token.controller.exception;

public class GoneException extends RuntimeException{
    public GoneException(String message){
        super(message);
    }
}
