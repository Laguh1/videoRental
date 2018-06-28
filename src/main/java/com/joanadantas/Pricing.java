package com.joanadantas;

public abstract class Pricing implements CalculatePrice{
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

    @Override
    public double calculateRentPrice(int daysRented){
        double totalPrice = 0;

        if(daysRented>1){

            if(daysRented <= numberOfFixedDays){
                totalPrice = pricePerDay;
            }else{
                totalPrice = pricePerDay+(daysRented-numberOfFixedDays)*pricePerDay;
            }
        }
        return totalPrice;
    }
}
