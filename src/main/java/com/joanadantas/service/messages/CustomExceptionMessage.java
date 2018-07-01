package com.joanadantas.service.messages;

public class CustomExceptionMessage {

    String message;

    public CustomExceptionMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
