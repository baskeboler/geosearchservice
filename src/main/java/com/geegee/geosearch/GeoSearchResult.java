package com.geegee.geosearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Victor Gil on 26/05/16.
 */
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GeoSearchResult {
    @JsonProperty("place_id")
    private Long id;
    @JsonProperty("lat")
    private BigDecimal latitude;
    @JsonProperty("lon")
    private BigDecimal longitude;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("importance")
    private BigDecimal importance;
    @JsonProperty("icon")
    private String iconUrl;

}
