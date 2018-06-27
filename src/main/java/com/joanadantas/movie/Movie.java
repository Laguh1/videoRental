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
   // private Pricing pricing;
    private String pricing;
    private final String publishDate;



    public String getTitle() {
        return title;
    }

    public String getPricing() {
        return pricing;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public Movie (String title, String publishDate, String pricing) {
        this.title = title;
        this.publishDate = publishDate;
       // this.pricing = getPricingByMovieAge(publishDate);
        this.pricing = pricing;
    }

    Pricing getPricingByMovieAge(LocalDate publishDate) {
                LocalDate now = LocalDate.now();
        if (now.isBefore(publishDate.plusDays(NUMBER_OF_DAYS_FOR_NEW_RELEASE))){
            return new NewReleasePricing();
        } else if (now.isAfter(publishDate.plusDays(NUMBER_OF_DAYS_FOR_OLD_MOVIE))){
            return new OldMoviePricing();
        } else{
            return new RegularPricing();
        }
    }


}
