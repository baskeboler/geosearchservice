package com.geegee.geosearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Victor Gil on 26/05/16.
 */
@Data
public class Address implements Serializable{
    @JsonProperty("house_number")
    private String houseNumber;
    @JsonProperty("road")
    private String road;
    @JsonProperty("city")
    private String city;
    @JsonProperty("county")
    private String county;
    @JsonProperty("state")
    private String state;
    @JsonProperty("postcode")
    private String postcode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("country_code")
    private String countryCode;

}
