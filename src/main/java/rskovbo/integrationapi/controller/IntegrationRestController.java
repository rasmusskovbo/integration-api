package rskovbo.integrationapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import rskovbo.integrationapi.model.dto.Forecast;
import rskovbo.integrationapi.model.dto.SummaryDTO;
import rskovbo.integrationapi.service.ForecastService;

@CrossOrigin("*")
@RestController
public class IntegrationRestController {

    private final ForecastService forecastService;

    public IntegrationRestController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("weather/forecast/{unit}/{location}")
    public Forecast getFiveDayForecast(
            @PathVariable String unit,
            @PathVariable String location
    ) {
       return forecastService.getForecast(location, unit);
    }

    @GetMapping("weather/favorites/{unit}/{minimumTemperature}/locations={favorites}")
    public SummaryDTO getFavoritesBasedOffTemperature(
            @PathVariable String unit,
            @PathVariable double minimumTemperature,
            @PathVariable String[] favorites
    ) {
        return forecastService.getSummary(unit, minimumTemperature, favorites);
    }
}
