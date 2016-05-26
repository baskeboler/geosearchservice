package com.geegee.geosearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Victor Gil on 26/05/16.
 */
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString(callSuper = true)
public class ReverseGeoSearchResult extends GeoSearchResult {

    @JsonProperty("bounding_box")
    private List<BigDecimal> boundingBox;

    @JsonProperty("address")
    private Address address;
}
