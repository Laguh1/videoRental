package com.joanadantas.service.dto;

public class CustomErrorMessageDTO {

    String message;

    public CustomErrorMessageDTO(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
