package rskovbo.integrationapi.model.dto;

import rskovbo.integrationapi.service.TemperatureConverter;

import java.util.ArrayList;
import java.util.List;

public class SummaryDTO {

    private String requestedUnit;
    private double minimumTemperature;
    private List<SummaryLocationDTO> favorites;

    public SummaryDTO(String requestedUnit, double minimumTemperature) {
        this.requestedUnit = requestedUnit;
        this.minimumTemperature = minimumTemperature;
        this.favorites = new ArrayList<>();
    }

    public String getRequestedUnit() {
        return requestedUnit;
    }

    public void setRequestedUnit(String requestedUnit) {
        this.requestedUnit = requestedUnit;
    }

    public double getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(double minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public List<SummaryLocationDTO> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<SummaryLocationDTO> favorites) {
        this.favorites = favorites;
    }

    public void addFavorite(String locationName, double nextDayTemperature) {
        favorites.add(
                new SummaryLocationDTO(
                        locationName,
                        TemperatureConverter.convertToRequestedUnit(requestedUnit, nextDayTemperature)
                )
        );
    }
}
