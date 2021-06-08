package rskovbo.integrationapi.model.dto;

import rskovbo.integrationapi.model.database.Temperature;
import rskovbo.integrationapi.service.TemperatureConverter;

import java.util.ArrayList;
import java.util.List;

public class Forecast {

    private String locationName;
    private String requestedUnit;
    private long fetchedAt;
    private List<TemperatureData> temperatureData;

    public Forecast(String locationName, String unit) {
        this.locationName = locationName;
        this.requestedUnit = unit;
        this.fetchedAt = System.currentTimeMillis()/1000;
        this.temperatureData = new ArrayList<>();
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public long getFetchedAt() {
        return fetchedAt;
    }

    public void setFetchedAt(long fetchedAt) {
        this.fetchedAt = fetchedAt;
    }

    public List<TemperatureData> getTemperatureData() {
        return temperatureData;
    }

    public void setTemperatureData(List<TemperatureData> temperatureData) {
        this.temperatureData = temperatureData;
    }

    public void addTemperatureData(Temperature temperature) {
        long timestamp = temperature.getTimestamp();
        double temp = TemperatureConverter.convertToRequestedUnit(requestedUnit, temperature.getTemperature());
        TemperatureData tempData = new TemperatureData(timestamp, temp);
        this.temperatureData.add(tempData);
    }

}
