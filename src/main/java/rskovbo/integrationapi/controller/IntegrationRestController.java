package rskovbo.integrationapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rskovbo.integrationapi.model.dto.Forecast;
import rskovbo.integrationapi.service.ForecastService;
import rskovbo.integrationapi.service.OpenWeatherService;

@CrossOrigin("*")
@RestController
public class IntegrationRestController {

    private OpenWeatherService openWeatherService = new OpenWeatherService(new RestTemplate());
    private final ForecastService forecastService;

    @Autowired
    public IntegrationRestController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("test/{location}/")
    public ResponseEntity<Forecast> testDB(@PathVariable String location) {
        Forecast forecast = forecastService.getForecast(location);
        return new ResponseEntity<>(forecast, HttpStatus.OK);
    }

    /*
    @GetMapping("test/{location}/{unit}")
    public ResponseEntity<WeatherInfo> testAPI(@PathVariable String location, @PathVariable String unit) {
        WeatherInfo weatherInfo = openWeatherService.getForecast(location, unit);
        return new ResponseEntity<>(weatherInfo, HttpStatus.OK);
    }
    */
}
