package rskovbo.integrationapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import rskovbo.integrationapi.model.openweather.WeatherInfo;

import java.net.URI;

@Service
public class OpenWeatherService {

    private static long accessCounter = 0;
    private RestTemplate restTemplate;
    private String apiKey = "c8767d823a3d9d3803b2cfe08affebc4";
    private String url = "http://api.openweathermap.org/data/2.5/forecast";

    public OpenWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherInfo getForecast(String location) {
        try {
            URI uri = UriComponentsBuilder
                    .fromUriString(url)
                    .queryParam("q", location)
                    .queryParam("appid", apiKey)
                    .build()
                    .toUri();

            ResponseEntity<WeatherInfo> responseEntity = restTemplate.getForEntity(uri, WeatherInfo.class);
            accessCounter++;
            return responseEntity.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(400, "One or more cities not found.", e);
        }


    }

}
