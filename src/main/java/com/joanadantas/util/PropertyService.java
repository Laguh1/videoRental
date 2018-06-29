package com.joanadantas.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyService {

    private static PropertyService propertyService = null;
    String fileName = "pricing.properties";
    Properties pricingProperties;

    private PropertyService(){};

    public static PropertyService getInstance(){
        if (propertyService==null){
            propertyService = new PropertyService();
        }
        return propertyService;
    }

    public Properties loadAndGetPricingProperties() {
        if (pricingProperties == null) {
            pricingProperties = new Properties();
            try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
                pricingProperties.load(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return pricingProperties;
    }

    public int getPropertyInInteger(String propertyName, String defaultValue) {
        try {
            return Integer.parseInt(loadAndGetPricingProperties().getProperty(propertyName, defaultValue).trim());
        }catch (NumberFormatException nFEx){
            nFEx.printStackTrace();
            return 30;
        }
    }

    public String getProperty(String propertyName, String defaultValue) {
        return loadAndGetPricingProperties().getProperty(propertyName, defaultValue).trim();
    }
}
