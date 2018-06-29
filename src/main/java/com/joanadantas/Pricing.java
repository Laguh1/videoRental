package com.joanadantas;

import com.joanadantas.util.PropertyService;

public abstract class Pricing implements CalculatePrice{
    private final double pricePerDay;
    private final int numberOfFixedDays;
    private PropertyService propertyService;

    public Pricing (String priceProperty, String daysProperty){
        propertyService = new PropertyService();
        this.pricePerDay = propertyService.getPropertyInInteger(priceProperty);
        this.numberOfFixedDays = propertyService.getPropertyInInteger(daysProperty);
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
