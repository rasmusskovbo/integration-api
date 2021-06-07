package rskovbo.integrationapi.controller;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rskovbo.integrationapi.model.rest.Forecast;
import rskovbo.integrationapi.service.ForecastService;
import rskovbo.integrationapi.service.OpenWeatherService;

@CrossOrigin("*")
@RestController
public class IntegrationRestController {

    private OpenWeatherService openWeatherService = new OpenWeatherService(new RestTemplate());

    @Autowired
    public IntegrationRestController(ForecastService forecastService) {
    }

    @GetMapping("test/{location}/{unit}")
    public ResponseEntity<Forecast> testForecast(@PathVariable String location, @PathVariable String unit) {
        Forecast weatherInfo = openWeatherService.getForecast(location, unit);
        return new ResponseEntity<>(weatherInfo, HttpStatus.OK);
    }

}
