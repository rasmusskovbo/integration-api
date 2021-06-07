package rskovbo.integrationapi.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rskovbo.integrationapi.model.WeatherInfo;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenWeatherService {

    private RestTemplate restTemplate;
    private String apiKey = "c8767d823a3d9d3803b2cfe08affebc4";
    private String url = "http://api.openweathermap.org/data/2.5/forecast";

    public OpenWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherInfo getForecast(String location, String temp) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("q", location);
        parameters.put("appid", apiKey);

        ResponseEntity<WeatherInfo> responseEntity =
                restTemplate.getForEntity(url, WeatherInfo.class, parameters);

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
