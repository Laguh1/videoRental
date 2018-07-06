package com.joanadantas.service.messages;

import com.joanadantas.movie.Movie;

public class SuccessfulRentMessage implements Messaging{

    private final String customerId;
    private final String customerName;
    private final Movie rentedMovie;
    private final boolean isRentSuccessful;
    private final int amountToPay;

    public SuccessfulRentMessage(String customerId, String customerName, Movie rentedMovie, int amountToPay) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.rentedMovie = rentedMovie;
        this.isRentSuccessful = true;
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

    public boolean getIsRentSuccessful() {
        return isRentSuccessful;
    }

    public int getAmountToPay() {
        return amountToPay;
    }

}
