package br.com.dev.delivery.exception;

public class UserFoundException extends RuntimeException{
    public UserFoundException(String message){
        super(message);
    }
}
