package com.joanadantas.service.messages;

import com.joanadantas.movie.Movie;

public class SuccesfullRentMessage {

    private String customerId;
    private String customerName;
    private Movie rentedMovie;
    private String operationStatus;
    private int amountToPay;

    public SuccesfullRentMessage(String customerId, String customerName, Movie rentedMovie, String operationStatus, int amountToPay) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.rentedMovie = rentedMovie;
        this.operationStatus = operationStatus;
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

    public String getOperationStatus() {
        return operationStatus;
    }

    public int getAmountToPay() {
        return amountToPay;
    }

}
