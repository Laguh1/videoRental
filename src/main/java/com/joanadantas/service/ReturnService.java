package com.joanadantas.service;

import com.joanadantas.service.messages.SuccessfulReturnMessage;

public interface ReturnService {

    SuccessfulReturnMessage returnAMovie(String customerId, String movieId) throws CustomException;
}
