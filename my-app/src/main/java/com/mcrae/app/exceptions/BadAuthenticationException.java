package com.mcrae.app.exceptions;

public class BadAuthenticationException extends Exception{
    public BadAuthenticationException(String msg){
        super(msg);
    }
}
