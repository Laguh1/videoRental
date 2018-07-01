package com.joanadantas.service.messages;

public class CustomErrorMessage {

    String message;

    public CustomErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
