package com.joanadantas.service.messages;

import com.joanadantas.movie.Movie;

public class SuccessfulReturnMessage {

    private String customerId;
    private String customerName;
    private Movie returnedMovie;
    private boolean isReturnSuccessful;
    private int amountToPay;

    public SuccessfulReturnMessage(String customerId, String customerName, Movie returnedMovie, int amountToPay) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.returnedMovie = returnedMovie;
        this.isReturnSuccessful = true;
        this.amountToPay = amountToPay;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Movie getReturnedMovie() {
        return returnedMovie;
    }

    public boolean getReturnSuccessful() {
        return isReturnSuccessful;
    }

    public int getAmountToPay() {
        return amountToPay;
    }
}
