package rskovbo.integrationapi.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationInfo {

    @JsonProperty("dt")
    private long date;

    @JsonProperty("main")
    private TemperatureInfo temperatureInfo;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public TemperatureInfo getTemperatureInfo() {
        return temperatureInfo;
    }

    public void setTemperatureInfo(TemperatureInfo temperatureInfo) {
        this.temperatureInfo = temperatureInfo;
    }
}
