package com.joanadantas.movie;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.joanadantas.NewReleasePricing;
import com.joanadantas.OldMoviePricing;
import com.joanadantas.Pricing;
import com.joanadantas.RegularPricing;

import java.time.LocalDate;

@JsonPropertyOrder({"id","title","publishDate","isAvailable","returnDate", "rentDate","pricing"})
public class Movie {

    private static final int NUMBER_OF_DAYS_FOR_NEW_RELEASE = 3*30;
    private static final int NUMBER_OF_DAYS_FOR_OLD_MOVIE = 5*12*30;

    private final String id;
    private final String title;
    private final String publishDate;
    private boolean isAvailable;
    private String returnDate;
    private String rentDate;
    private final Pricing pricing;

    public Movie (String id, String title, String publishDate) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        this.isAvailable = true;
        this.returnDate = "";
        this.rentDate = "";
        this.pricing = getPricingByMovieAge();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public boolean getIsAvailable(){
        return isAvailable;
    }

    public String getReturnDate(){
        return returnDate;
    }

    public String getRentDate() {
        return rentDate;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    Pricing getPricingByMovieAge() {
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
