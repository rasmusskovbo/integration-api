package rskovbo.integrationapi.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rskovbo.integrationapi.model.Forecast;

@Service
public class OpenWeatherService {

    private RestTemplate restTemplate;
    private String apiKey = "c8767d823a3d9d3803b2cfe08affebc4";
    private String url = "http://api.openweathermap.org/data/2.5/forecast?q=";

    public OpenWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Forecast getForecast(String location, String temp) {
        url += location;
        url += "&appid=" + apiKey;
        System.out.println(url);
        ResponseEntity<Forecast> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Forecast>() {});
        return responseEntity.getBody();
    }

    /*
    public Forecast getWeather(String location) {
        url += location;
        url += "&appid=" + apiKey;
        System.out.println(url);
        ResponseEntity<Forecast> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Forecast>() {});
        return responseEntity.getBody();
    }

     */



}
