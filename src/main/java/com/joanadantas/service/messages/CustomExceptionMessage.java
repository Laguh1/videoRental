package com.joanadantas.service.messages;

public class CustomExceptionMessage implements Messaging{

    private final String message;
    private final boolean isOperationSuccessful;

    public CustomExceptionMessage(String message){
        this.message = message;
        this.isOperationSuccessful = false;
    }

    public String getMessage(){
        return message;
    }

    public boolean getIsOperationSuccessful(){
        return isOperationSuccessful;
    }
}
