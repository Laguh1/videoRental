package com.joanadantas.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.joanadantas.NewReleasePricing;
import com.joanadantas.OldMoviePricing;
import com.joanadantas.Pricing;
import com.joanadantas.RegularPricing;
import com.joanadantas.util.PropertyService;

import java.time.LocalDate;

@JsonPropertyOrder({"id","title","publishDate","isAvailable","returnDate", "rentDate","pricing"})
public class Movie {

    private static final String DEFAULT_NUMBER_OF_DAYS_FOR_NEW_RELEASE = "90";
    private static final String DEFAULT_NUMBER_OF_DAYS_FOR_OLD_MOVIE = "1825";
    private static final int NUMBER_OF_DAYS_FOR_NEW_RELEASE = Integer.parseInt(PropertyService.getInstance().getProperty("new_release_age_in_days", DEFAULT_PRICE_RATE));
    private static final int NUMBER_OF_DAYS_FOR_OLD_MOVIE = 5*12*30;




    private String id;
    private String title;
    private String publishDate;
    private boolean isAvailable;
    private String returnDate;
    private String rentDate;
    @JsonIgnore
    private Pricing pricing;

    public Movie(){

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

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setPricing() {
        LocalDate publishDateInDate = LocalDate.parse(publishDate);
        LocalDate now = LocalDate.now();
        if (now.isBefore(publishDateInDate.plusDays(NUMBER_OF_DAYS_FOR_NEW_RELEASE))){
            this.pricing = new NewReleasePricing();
        } else if (now.isAfter(publishDateInDate.plusDays(NUMBER_OF_DAYS_FOR_OLD_MOVIE))){
            this.pricing = new OldMoviePricing();
        } else{
            this.pricing = new RegularPricing();
        }
    }

    //This is to simplify MovieTest
    public Movie (String id, String title, String publishDate) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        this.isAvailable = true;
        this.returnDate = "";
        this.rentDate = "";
        this.pricing = getPricingByMovieAge();
    }

    //This is to simplify MovieTest
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
