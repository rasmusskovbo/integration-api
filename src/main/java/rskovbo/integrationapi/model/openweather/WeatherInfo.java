package rskovbo.integrationapi.model.openweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {
    private String location;

    @JsonProperty("city")
    private void unpackCity(Map<String,Object> city) {
        this.location = (String) city.get("name");
    }

    @JsonProperty("list")
    private List<LocationInfo> forecast;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<LocationInfo> getForecast() {
        return forecast;
    }

    public void setForecast(List<LocationInfo> forecast) {
        this.forecast = forecast;
    }
}
