package com.azazafizer.server_v1.common.exception;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message){
        super(message);
    }
}
