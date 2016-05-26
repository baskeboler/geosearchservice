package com.geegee.geosearch.service;

import com.geegee.geosearch.GeoSearchResult;
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
    private String searchUrl;

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
        URIBuilder uriBuilder = new URIBuilder(searchUrl);//URIBuilder.fromUri(searchUrlBase);
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
}
