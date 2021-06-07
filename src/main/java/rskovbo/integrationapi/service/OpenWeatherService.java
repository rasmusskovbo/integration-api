package rskovbo.integrationapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import rskovbo.integrationapi.model.rest.Forecast;

import java.net.URI;

@Service
public class OpenWeatherService {

    private RestTemplate restTemplate;
    private String apiKey = "c8767d823a3d9d3803b2cfe08affebc4";
    private String url = "http://api.openweathermap.org/data/2.5/forecast";

    public OpenWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Forecast getForecast(String location, String temp) {
        URI uri = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("q", location)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .build()
                .toUri();

        ResponseEntity<Forecast> responseEntity = restTemplate.getForEntity(uri, Forecast.class);

        return responseEntity.getBody();
    }

}
