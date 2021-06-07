package rskovbo.integrationapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Forecast {

    @SuppressWarnings("unchecked")
    @JsonProperty("list")
    private void days(Map<String,Object> list) {
        Map<String, Object> main = (Map<String,Object>)list.get("main");
        new WeatherInfo((int) main.get("dt"), (double) main.get("temp"), "Celsius"); // TODO Dobbeltcheck input/control af temperatur
    }

}
