package com.geegee.geosearch;

import com.geegee.geosearch.service.GeoSearchService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.net.URISyntaxException;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.geegee.geosearch.service")
public class GeoSearchServiceConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer getConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) throws URISyntaxException {
        ApplicationContext context = new AnnotationConfigApplicationContext(GeoSearchServiceConfiguration.class);
        GeoSearchService service = context.getBean(GeoSearchService.class);

        List<GeoSearchResult> results = service.search("montevideo");
        results.forEach(geoSearchResult -> System.out.println(geoSearchResult.toString()));

        results.stream()
                .map(geoSearchResult -> {
                    try {
                        return service.reverseSearch(geoSearchResult.getLatitude(), geoSearchResult.getLongitude());
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(reverseGeoSearchResult -> reverseGeoSearchResult != null)
                .forEach(reverseGeoSearchResult -> System.out.println(reverseGeoSearchResult.toString()));
    }
}
