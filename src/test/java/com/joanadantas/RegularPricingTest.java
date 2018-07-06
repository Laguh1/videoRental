package com.joanadantas;

import com.joanadantas.service.pricing.Pricing;
import com.joanadantas.service.pricing.RegularPricing;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegularPricingTest {

    private static final int DAYS_UNDER_OR_OVER_FIXED = 1;

    Pricing objectUnderTest = new RegularPricing();
    private final double pricePerDay = objectUnderTest.getPricePerDay();
    private final int numberOfFixedDays = objectUnderTest.getNumberOfFixedDays();

    @Test
    public void calculateRentPrice_UnderDayLimit_ShouldReturnFixedprice() {

        int numberOfRentedDays = numberOfFixedDays > DAYS_UNDER_OR_OVER_FIXED ? numberOfFixedDays - DAYS_UNDER_OR_OVER_FIXED : numberOfFixedDays;
        double finalPrice = objectUnderTest.calculateRentPrice(numberOfRentedDays);

        assertEquals(pricePerDay, finalPrice, 0.01);
    }

    @Test
    public void calculateRentPrice_SameAsDayLimit_ShouldReturnFixedprice() {

        int numberOfRentedDays = numberOfFixedDays;
        double finalPrice = objectUnderTest.calculateRentPrice(numberOfRentedDays);

        assertEquals(pricePerDay, finalPrice, 0.01);
    }

    @Test
    public void calculateRentPrice_OverDayLimit_ShouldReturnFixedpriceTimesDays() {

        int numberOfRentedDays = numberOfFixedDays + DAYS_UNDER_OR_OVER_FIXED;
        double finalPrice = objectUnderTest.calculateRentPrice(numberOfRentedDays);
        double expectedPrice = (numberOfRentedDays - numberOfFixedDays + 1) * pricePerDay;

        assertEquals(expectedPrice, finalPrice, 0.01);
    }
}