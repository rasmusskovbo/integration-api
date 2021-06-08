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
        try {
            return forecastService.getForecast(location, unit);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City not found.", e);
        }

    }

    @GetMapping("weather/favorites/{unit}/{minimumTemperature}/locations={favorites}")
    public SummaryDTO getFavoritesBasedOffTemperature(
            @PathVariable String unit,
            @PathVariable double minimumTemperature,
            @PathVariable String[] favorites
    ) {
        try {
            return forecastService.getSummary(unit, minimumTemperature, favorites);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in one or more cities.");
        }
    }

}
