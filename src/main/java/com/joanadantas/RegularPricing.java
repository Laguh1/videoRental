package com.joanadantas;

import org.springframework.beans.factory.annotation.Autowired;

public class RegularPricing extends Pricing {

    @Autowired
    public RegularPricing(){
        super("regular_rate","regular_price", "regular_days");
    }

}
