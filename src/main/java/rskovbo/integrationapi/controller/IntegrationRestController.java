package rskovbo.integrationapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rskovbo.integrationapi.model.dto.Forecast;
import rskovbo.integrationapi.model.dto.SummaryDTO;
import rskovbo.integrationapi.service.ForecastService;
import rskovbo.integrationapi.service.SummaryService;

@CrossOrigin("*")
@RestController
public class IntegrationRestController {

    private final ForecastService forecastService;
    private final SummaryService summaryService;

    public IntegrationRestController(ForecastService forecastService, SummaryService summaryService) {
        this.forecastService = forecastService;
        this.summaryService = summaryService;
    }

    @GetMapping("weather/forecast/{unit}/{location}")
    public ResponseEntity<Forecast> getFiveDayForecast(
            @PathVariable String unit,
            @PathVariable String location
    ) {
        Forecast forecast = forecastService.getForecast(location, unit);
        return new ResponseEntity<>(forecast, HttpStatus.OK);
    }

    @GetMapping("weather/favorites/{unit}/{minimumTemperature}/locations={favorites}")
    public ResponseEntity<SummaryDTO> getFavoritesBasedOffTemperature(
            @PathVariable String unit,
            @PathVariable double minimumTemperature,
            @PathVariable String[] favorites
    ) {
        SummaryDTO summmary = forecastService.getSummary(unit, minimumTemperature, favorites);
        return new ResponseEntity<>(summmary, HttpStatus.OK);
    }

}
