package rskovbo.integrationapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rskovbo.integrationapi.model.dto.Forecast;
import rskovbo.integrationapi.model.database.Location;
import rskovbo.integrationapi.model.database.Temperature;
import rskovbo.integrationapi.model.dto.SummaryDTO;
import rskovbo.integrationapi.model.openweather.WeatherInfo;
import rskovbo.integrationapi.model.openweather.LocationInfo;
import rskovbo.integrationapi.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ForecastService {

    private final LocationRepository locationRepository;
    private final OpenWeatherService openWeatherService = new OpenWeatherService(new RestTemplate());
    private long expirationTimeLimit = 10800; // 3-Hour interval

    public ForecastService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Forecast getForecast(String location, String unit) {
        Forecast forecast = new Forecast(location, unit);
        Optional<Location> locationData = locationRepository.findLocationByName(location);

        if (locationData.isEmpty()) {
            Location newLocation = updateLocationInDatabase(location);

            for (Temperature temp : newLocation.getTemperatures()) {
                forecast.addTemperatureData(temp);
            }

        } else if (locationData.get().getLastUpdated() < (System.currentTimeMillis()/1000)-expirationTimeLimit) {
            locationRepository.deleteById(locationData.get().getId());
            Location newLocation = updateLocationInDatabase(location);

            for (Temperature temp : newLocation.getTemperatures()) {
                forecast.addTemperatureData(temp);
            }

        } else {
            for (Temperature temperature : locationData.get().getTemperatures()) {
                forecast.addTemperatureData(temperature);
            }
        }

        return forecast;
    }

    public SummaryDTO getSummary(String unit, double minimumTemperature, String[] favorites) {

        SummaryDTO summary = new SummaryDTO(unit, minimumTemperature);
        long nextDayTimeStamp = (System.currentTimeMillis()/1000)+86400;

        // For each location in the list of favorites, check if data is available in database.
        for (String location : favorites) {
            Optional<Location> dbLocation = locationRepository.findLocationByName(location);

            // If not available, contact API for update
            if (dbLocation.isEmpty()) {

                Location newLocation = updateLocationInDatabase(location);
                findConvertAddTemperature(unit, minimumTemperature, summary, nextDayTimeStamp, newLocation.getName(),newLocation.getTemperatures());

            } else if (dbLocation.get().getLastUpdated() < (System.currentTimeMillis()/1000)-expirationTimeLimit) {

                locationRepository.deleteById(dbLocation.get().getId());
                Location newLocation = updateLocationInDatabase(location);
                findConvertAddTemperature(unit, minimumTemperature, summary, nextDayTimeStamp, newLocation.getName(),newLocation.getTemperatures());

            } else {

                findConvertAddTemperature(unit, minimumTemperature, summary, nextDayTimeStamp, dbLocation.get().getName(), dbLocation.get().getTemperatures());

            }

        }

        return summary;

    }

    private void findConvertAddTemperature(String unit, double minimumTemperature, SummaryDTO summary, long nextDayTimeStamp, String locationName, List<Temperature> temperatures) {
        double nextDayTemperature = 0;

        for (Temperature temp : temperatures) {
            if (temp.getTimestamp() > nextDayTimeStamp) {
                nextDayTemperature = temp.getTemperature(); // Sets next day temperature (+24h)
                break;
            }
        }

        if (nextDayTemperature >= TemperatureConverter.convertToKelvin(unit, minimumTemperature)) {
            summary.addFavorite(locationName, nextDayTemperature);
        }
    }

    public Location updateLocationInDatabase(String location) {
        WeatherInfo weatherInfo = openWeatherService.getForecast(location);

        // Map JSON response to correct format
        // Location info
        Location newLocation = new Location();
        newLocation.setLastUpdated(System.currentTimeMillis()/1000);
        newLocation.setName(location);
        locationRepository.save(newLocation);

        // Map all temperatures and timestamps to correct format
        for (LocationInfo locationInfo : weatherInfo.getForecast()) {

            Temperature temperature = new Temperature();
            temperature.setTemperature(locationInfo.getTemperatureInfo().getTemperature());
            temperature.setTimestamp(locationInfo.getTimestamp());
            temperature.setLocation(newLocation);
            newLocation.addTemperature(temperature);

        }

        locationRepository.save(newLocation);

        return newLocation;
    }

}
