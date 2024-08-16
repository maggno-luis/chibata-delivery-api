package br.com.dev.delivery.exception;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(String message){
        super(message);
    }

}
