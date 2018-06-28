package com.joanadantas;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegularPricingTest {

    private static final int NUMBER_OF_RENTED_DAYS_UNDER_LIMIT = 2;
    private static final int NUMBER_OF_RENTED_DAYS_IS_LIMIT = 3;

    private static final int NUMBER_OF_RENTED_DAYS_OVER_LIMIT = 2;

    Pricing objectUndertest = new RegularPricing();


    @Test
    public void calculateRentPrice_UnderDayLimit_ShouldReturnFixedprice() {

        double finalPrice = objectUndertest.calculateRentPrice(NUMBER_OF_RENTED_DAYS_UNDER_LIMIT);

        assertEquals(objectUndertest.getPricePerDay(), finalPrice, 0.01);

    }

    @Test
    public void calculateRentPrice_SameAsDayLimit_ShouldReturnFixedprice() {

        double finalPrice = objectUndertest.calculateRentPrice(NUMBER_OF_RENTED_DAYS_IS_LIMIT);

        assertEquals(objectUndertest.getPricePerDay(), finalPrice, 0.01);

    }
}