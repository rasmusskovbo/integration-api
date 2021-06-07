package rskovbo.integrationapi.model.openweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationInfo {

    @JsonProperty("dt")
    private long timestamp;

    @JsonProperty("main")
    private TemperatureInfo temperatureInfo;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public TemperatureInfo getTemperatureInfo() {
        return temperatureInfo;
    }

    public void setTemperatureInfo(TemperatureInfo temperatureInfo) {
        this.temperatureInfo = temperatureInfo;
    }
}
