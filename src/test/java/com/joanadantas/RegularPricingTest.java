package com.joanadantas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegularPricingTest {

    private static final int DAYS_UNDER_OR_OVER_FIXED = 1;

    Pricing objectUndertest = new RegularPricing();
    private final double pricePerDay = objectUndertest.getPricePerDay();
    private final int numberOfFixedDays = objectUndertest.getNumberOfFixedDays();

    @Test
    public void calculateRentPrice_UnderDayLimit_ShouldReturnFixedprice() {

        int numberOfRentedDays = numberOfFixedDays > DAYS_UNDER_OR_OVER_FIXED ? numberOfFixedDays - DAYS_UNDER_OR_OVER_FIXED : numberOfFixedDays;
        double finalPrice = objectUndertest.calculateRentPrice(numberOfRentedDays);

        assertEquals(pricePerDay, finalPrice, 0.01);
    }

    @Test
    public void calculateRentPrice_SameAsDayLimit_ShouldReturnFixedprice() {

        int numberOfRentedDays = numberOfFixedDays;
        double finalPrice = objectUndertest.calculateRentPrice(numberOfRentedDays);

        assertEquals(pricePerDay, finalPrice, 0.01);
    }

    @Test
    public void calculateRentPrice_OverDayLimit_ShouldReturnFixedpriceTimesDays() {

        int numberOfRentedDays = numberOfFixedDays + DAYS_UNDER_OR_OVER_FIXED;
        double finalPrice = objectUndertest.calculateRentPrice(numberOfRentedDays);
        double expectedPrice = (numberOfRentedDays - numberOfFixedDays + 1) * pricePerDay;

        assertEquals(expectedPrice, finalPrice, 0.01);
    }
}