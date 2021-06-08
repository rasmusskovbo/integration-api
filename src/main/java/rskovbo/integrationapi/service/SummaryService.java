package rskovbo.integrationapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rskovbo.integrationapi.model.database.Location;
import rskovbo.integrationapi.model.dto.SummaryDTO;
import rskovbo.integrationapi.model.dto.SummaryLocationDTO;
import rskovbo.integrationapi.model.openweather.WeatherInfo;
import rskovbo.integrationapi.repository.LocationRepository;

import java.util.Optional;

@Service
public class SummaryService {

    private final LocationRepository locationRepository;
    private OpenWeatherService openWeatherService = new OpenWeatherService(new RestTemplate());

    public SummaryService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public SummaryDTO getSummary(String unit, double minimumTemperature, String[] favorites) {
        SummaryDTO summary = new SummaryDTO(unit, minimumTemperature);
        long nextDay = (System.currentTimeMillis()/1000)+86400;

        // For each location in the list of favorites, check if data is available in database.
        for (SummaryLocationDTO location : summary.getFavorites()) {
            Optional<Location> dbLocation = locationRepository.findLocationByName(location.getName());

            // If not available, contact API for update
            if (dbLocation.isEmpty()) {
                WeatherInfo weatherInfo = openWeatherService.getForecast(location.getName());
            }
        }
        return null;
    }
}
