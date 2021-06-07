package rskovbo.integrationapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rskovbo.integrationapi.model.dto.Forecast;
import rskovbo.integrationapi.model.database.Location;
import rskovbo.integrationapi.model.database.Temperature;
import rskovbo.integrationapi.model.openweather.WeatherInfo;
import rskovbo.integrationapi.model.openweather.LocationInfo;
import rskovbo.integrationapi.repository.LocationRepository;
import rskovbo.integrationapi.repository.TemperatureRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ForecastService {

    private final LocationRepository locationRepository;
    private final TemperatureRepository temperatureRepository;
    private OpenWeatherService openWeatherService = new OpenWeatherService(new RestTemplate());

    @Autowired
    public ForecastService(LocationRepository locationRepository, TemperatureRepository temperatureRepository) {
        this.locationRepository = locationRepository;
        this.temperatureRepository = temperatureRepository;
    }

    // TODO THROW hvis location ikke kendt
    public Forecast getForecast(String location) {
        Forecast forecast = new Forecast(location);
        Optional<Location> locationData = locationRepository.findLocationByName(location);
        // If no data available TODO Check for 3h expiration as well
        if (locationData.isEmpty()) {
            // If no data is available or data is expired contact API for update
            WeatherInfo weatherInfo = openWeatherService.getForecast(location, "celsius");

            // Map JSON response to correct format
            // Location info
            Location newLocation = new Location();
            newLocation.setLastUpdated(System.currentTimeMillis());
            newLocation.setName(location);
            locationRepository.save(newLocation);

            // Map all temperatures and timestamps to correct format
            for (LocationInfo locationInfo : weatherInfo.getForecast()) {
                Temperature temperature = new Temperature();
                temperature.setTemperature(locationInfo.getTemperatureInfo().getTemperature());
                temperature.setTimestamp(locationInfo.getTimestamp());
                temperature.setLocation(newLocation);
                temperatureRepository.save(temperature);
                newLocation.addTemperature(temperature);
            }

            for (Temperature temp : newLocation.getTemperatures()) {
                forecast.addTemperatureData(temp);
            }
            return forecast;

        }

        long id = locationData.get().getId();
        Location refreshedLocation = locationRepository.getById(id);

        for (Temperature temperature : refreshedLocation.getTemperatures()) {
            forecast.addTemperatureData(temperature);
        }
        return forecast;
    }


}
