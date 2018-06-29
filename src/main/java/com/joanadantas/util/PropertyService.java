package com.joanadantas.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyService {

    String fileName = "pricing.properties";
    Properties pricingProperties ;

    public Properties loadAndGetPricingProperties(){
        if(pricingProperties==null){
            pricingProperties = new Properties();
            try(InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileName)){
                pricingProperties.load(input);
            }catch(IOException ex){
                ex.printStackTrace();
            }
            return pricingProperties;
        }else{
            return pricingProperties;
        }
    }

    public int getPropertyInInteger(String propertyName) {
        return Integer.parseInt(loadAndGetPricingProperties().getProperty(propertyName).trim());
    }

}
