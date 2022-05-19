package com.company.error;

public class MissingItemException extends RuntimeException{

    public MissingItemException(String message){
        super(message);
    }

}
