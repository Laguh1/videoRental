package com.joanadantas;

import com.joanadantas.util.PropertyService;

public abstract class Pricing implements CalculatePrice{

    private final String pricingRate;
    private final double pricePerDay;
    private final int numberOfFixedDays;

    public Pricing (String rateProperty, String priceProperty, String daysProperty){
        this.pricingRate = PropertyService.getInstance().getProperty(rateProperty);
        this.pricePerDay = PropertyService.getInstance().getPropertyInInteger(priceProperty);
        this.numberOfFixedDays = PropertyService.getInstance().getPropertyInInteger(daysProperty);
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public int getNumberOfFixedDays() {
        return numberOfFixedDays;
    }

    public String getPricingRate() {
        return pricingRate;
    }

    @Override
    public double calculateRentPrice(int daysRented){
        double totalPrice = 0;

        if(daysRented>0){

            if(daysRented <= numberOfFixedDays){
                totalPrice = pricePerDay;
            }else{
                totalPrice = pricePerDay+(daysRented-numberOfFixedDays)*pricePerDay;
            }
        }
        return totalPrice;
    }
}
