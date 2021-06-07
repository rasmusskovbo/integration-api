package rskovbo.integrationapi.model.dto;

import rskovbo.integrationapi.model.database.Temperature;

import java.util.List;

public class Forecast {

    private String locationName;
    private long fetchedAt;
    private List<TemperatureData> temperatureData;

    public Forecast(String locationName) {
        this.locationName = locationName;
        this.fetchedAt = System.currentTimeMillis();
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
        this.temperatureData.add(new TemperatureData(temperature.getTimestamp(), temperature.getTemperature()));
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "locationName='" + locationName + '\'' +
                ", fetchedAt=" + fetchedAt +
                ", temperatureData=" + temperatureData +
                '}';
    }
}
