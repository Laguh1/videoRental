package com.joanadantas.movie;

import com.joanadantas.NewReleasePricing;
import com.joanadantas.OldMoviePricing;
import com.joanadantas.RegularPricing;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    private static final String MOVIE_ID = "001";
    private static final String TITLE = "Any Movie Title";
    private static final String REGULAR_PUBLISH_DATE = "2017-06-16";
    private static final String NEW_RELEASE_PUBLISH_DATE = "2018-06-16";
    private static final String OLD_MOVIE_PUBLISH_DATE = "2011-06-16";

    private Movie objectUnderTest;

    @Test
    public void getPricingByMovieAge_BeforeNewReleaseDate_ShouldReturnRegularPricingMovie() {
        objectUnderTest = new Movie(MOVIE_ID, TITLE, REGULAR_PUBLISH_DATE);
        assertTrue(objectUnderTest.getPricing() instanceof RegularPricing);
    }

    @Test
    public void getPricingByMovieAge_AfterNewReleaseDate_ShouldReturnNewReleasePricingMovie() {
        objectUnderTest = new Movie(MOVIE_ID, TITLE, NEW_RELEASE_PUBLISH_DATE);
        assertTrue(objectUnderTest.getPricing() instanceof NewReleasePricing);
    }

    @Test
    public void getPricingByMovieAge_BeforeRegularDate_ShouldReturnOldMoviePricingMovie() {
        objectUnderTest = new Movie(MOVIE_ID, TITLE, OLD_MOVIE_PUBLISH_DATE);
        assertTrue(objectUnderTest.getPricing() instanceof OldMoviePricing);
    }
}