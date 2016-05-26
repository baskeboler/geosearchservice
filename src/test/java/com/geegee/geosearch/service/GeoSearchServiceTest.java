package com.geegee.geosearch.service;

import com.geegee.geosearch.GeoSearchResult;
import com.geegee.geosearch.GeoSearchServiceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * Created by Victor Gil on 26/05/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GeoSearchServiceConfiguration.class)
public class GeoSearchServiceTest {

    @Autowired
    private GeoSearchService geoSearchService;

    @Test
    public void search() throws Exception {

        List<GeoSearchResult> results = geoSearchService.search("montevideo");
        assertFalse(results.isEmpty());

    }
}