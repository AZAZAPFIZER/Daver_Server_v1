package com.azazafizer.server_v1.common.exception;

public class InternalServerException extends RuntimeException{
    public InternalServerException(String message){
        super(message);
    }
}
