package com.joanadantas;

public abstract class Pricing {
    private final double pricePerDay;
    private final int numberOfFixedDays;

    public Pricing (double pricePerDay, int numberOfFixedDays){
        this.pricePerDay = pricePerDay;
        this.numberOfFixedDays = numberOfFixedDays;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public int getNumberOfFixedDays() {
        return numberOfFixedDays;
    }
}
