package com.geegee.geosearch.service;

import com.geegee.geosearch.GeoSearchResult;
import com.geegee.geosearch.ReverseGeoSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Gil on 26/05/16.
 */
@Component
@PropertySource("classpath:application.properties")
@Slf4j
public class GeoSearchService {
    @Value("${com.geegee.geo-search.url}")
    private String geocodeBaseUrl;

    @Value("${com.geegee.reverse-geo-search.url}")
    private String reverseGeocodeBaseUrl;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        log.info("Starting GeoSearchService");
    }

    /**
     * Search list.
     *
     * @param query the query
     * @return the list
     * @throws URISyntaxException the uri syntax exception
     */
    public List<GeoSearchResult> search(String query) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(geocodeBaseUrl);//URIBuilder.fromUri(searchUrlBase);
        URI uri = uriBuilder
                .addParameter("format", "json")
                .addParameter("q", query)
                .build();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<ArrayList<GeoSearchResult>> responseEntity =
                restTemplate.exchange(
                        uri, HttpMethod.GET, null,
                        new ParameterizedTypeReference<ArrayList<GeoSearchResult>>() {
                        });
        return responseEntity.getBody();
    }

    public ReverseGeoSearchResult reverseSearch(BigDecimal lat, BigDecimal lon) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(reverseGeocodeBaseUrl);
        URI uri = builder.addParameter("format", "json")
                .addParameter("lat", lat.toString())
                .addParameter("lon", lon.toString())
                .build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ReverseGeoSearchResult> responseEntity =
                restTemplate.getForEntity(uri, ReverseGeoSearchResult.class);
//        exchange(uri, HttpMethod.GET, null,
//                new ParameterizedTypeReference<ReverseGeoSearchResult>() {
//                });
        return responseEntity.getBody();
    }
}
