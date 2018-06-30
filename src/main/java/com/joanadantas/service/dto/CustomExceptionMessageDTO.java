package com.joanadantas.service.dto;

public class CustomExceptionMessageDTO {

    String message;

    public CustomExceptionMessageDTO(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
