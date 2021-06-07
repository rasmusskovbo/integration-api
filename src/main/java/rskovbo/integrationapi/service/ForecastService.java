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
            System.out.println("Inside locationData.isEmpty");
            // If no data is available or data is expired contact API for update
            WeatherInfo weatherInfo = openWeatherService.getForecast(location, "celsius");

            // Map JSON response to correct DTO format
            // Location info
            Location newLocation = new Location();
            newLocation.setLastUpdated(System.currentTimeMillis());
            newLocation.setName(location);
            locationRepository.save(newLocation);

            // Map all temperatures and timestamps to correct DTO format
            for (LocationInfo locationInfo : weatherInfo.getForecast()) {
                Temperature temperature = new Temperature();
                temperature.setTemperature(locationInfo.getTemperatureInfo().getTemperature());
                temperature.setTimestamp(locationInfo.getTimestamp());
                temperature.setLocation(newLocation);
                System.out.println(temperature);
                temperatureRepository.save(temperature);
            }
            Optional<Location> optionalForId = locationRepository.findLocationByName(location);
            Long id = optionalForId.get().getId();
            Location refreshedLocation = locationRepository.getById(id);
            for (Temperature temp : refreshedLocation.getTemperatures()) {
                System.out.println("Adding temp data");
                forecast.addTemperatureData(temp);
            }
            return forecast;

        }
        return getLocationAndTemperatures(forecast);
    }

    public Forecast getLocationAndTemperatures(Forecast forecast) {
        System.out.println("getLocationAndTemps");

        Optional<Location> optionalForId = locationRepository.findLocationByName(forecast.getLocationName());
        Long id = optionalForId.get().getId();
        Location refreshedLocation = locationRepository.getById(id);

        List<Temperature> temperatures = refreshedLocation.getTemperatures();
        System.out.println(temperatures);

        for (Temperature temp : temperatures) {
            System.out.println("Adding temp data");
            forecast.addTemperatureData(temp);
        }
        System.out.println(forecast.toString());
        return forecast;
    }
}
