package rskovbo.integrationapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {

    @JsonProperty("list")
    private List<LocationInfo> forecast;

    public List<LocationInfo> getForecast() {
        return forecast;
    }

    public void setForecast(List<LocationInfo> forecast) {
        this.forecast = forecast;
    }
}
