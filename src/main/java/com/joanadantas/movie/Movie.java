package com.joanadantas.movie;

import com.joanadantas.NewReleasePricing;
import com.joanadantas.OldMoviePricing;
import com.joanadantas.Pricing;
import com.joanadantas.RegularPricing;

import java.time.LocalDate;

public class Movie {

    private static final int NUMBER_OF_DAYS_FOR_NEW_RELEASE = 3*30;
    private static final int NUMBER_OF_DAYS_FOR_OLD_MOVIE = 5*12*30;

    private final String title;
    private Pricing pricing;
    private final String publishDate;

    public String getTitle() {
        return title;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public Movie (String title, String publishDate) {
        this.title = title;
        this.publishDate = publishDate;
        this.pricing = getPricingByMovieAge(publishDate);
    }

    Pricing getPricingByMovieAge(String publishDate) {
        LocalDate publishDateInDate = LocalDate.parse(publishDate);
                LocalDate now = LocalDate.now();
        if (now.isBefore(publishDateInDate.plusDays(NUMBER_OF_DAYS_FOR_NEW_RELEASE))){
            return new NewReleasePricing();
        } else if (now.isAfter(publishDateInDate.plusDays(NUMBER_OF_DAYS_FOR_OLD_MOVIE))){
            return new OldMoviePricing();
        } else{
            return new RegularPricing();
        }
    }
}
