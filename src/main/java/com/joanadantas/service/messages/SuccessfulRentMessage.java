package com.joanadantas.service.messages;

import com.joanadantas.movie.Movie;

public class SuccessfulRentMessage {

    private String customerId;
    private String customerName;
    private Movie rentedMovie;
    private boolean isOperationSuccessful;
    private int amountToPay;

    public SuccessfulRentMessage(String customerId, String customerName, Movie rentedMovie, int amountToPay) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.rentedMovie = rentedMovie;
        this.isOperationSuccessful = true;
        this.amountToPay = amountToPay;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Movie getRentedMovie() {
        return rentedMovie;
    }

    public boolean getOperationStatus() {
        return isOperationSuccessful;
    }

    public int getAmountToPay() {
        return amountToPay;
    }

}
