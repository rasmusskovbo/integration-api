package rskovbo.integrationapi.model;

import java.time.LocalDate;

public class WeatherInfo {
    private int date;
    private double temperature;
    private String unit;

    public WeatherInfo(int date, double temperature, String unit) {
        this.date = date;
        this.temperature = temperature;
        this.unit = unit;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
