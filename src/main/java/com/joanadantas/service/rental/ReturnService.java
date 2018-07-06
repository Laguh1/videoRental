package com.joanadantas.service.rental;

import com.joanadantas.service.CustomException;
import com.joanadantas.service.messages.SuccessfulReturnMessage;

public interface ReturnService {

    SuccessfulReturnMessage returnAMovie(String customerId, String movieId) throws CustomException;
}
