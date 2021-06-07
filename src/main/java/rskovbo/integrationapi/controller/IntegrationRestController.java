package rskovbo.integrationapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rskovbo.integrationapi.model.Forecast;
import rskovbo.integrationapi.rest.OpenWeatherService;
import rskovbo.integrationapi.model.WeatherInfo;

import java.util.ArrayList;

@CrossOrigin("*")
@RestController
public class IntegrationRestController {
OpenWeatherService openWeatherService = new OpenWeatherService(new RestTemplate());

    @GetMapping("test/{location}/{unit}")
    public ResponseEntity<Forecast> testForecast(@PathVariable String location, @PathVariable String unit) {
        Forecast forecast = openWeatherService.getForecast(location, unit);
        return new ResponseEntity<>(forecast, HttpStatus.OK);
    }

    @GetMapping("weather/{location}/{unit}")
    public ResponseEntity<Forecast> getFiveDayForecast(@PathVariable String location, @PathVariable String unit) {
        Forecast forecast = openWeatherService.getForecast(location, unit);
        return null;
    }

}
