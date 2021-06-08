package rskovbo.integrationapi.service;

public class TemperatureConverter {

    public static double convertToRequestedUnit(String unit, double temperature) {
        if (unit.equals("celsius")) {
            return Math.round(temperature - 273.15);
        } else if (unit.equals("fahrenheit")) {
            return Math.round((temperature * 1.8) - 459.67);
        } else {
            return Math.round(temperature);
        }
    }

    public static double convertToKelvin(String unit, double temperature) {
        if (unit.equals("celsius")) {
            return Math.round(temperature + 273.15);
        } else if (unit.equals("fahrenheit")) {
            return Math.round((temperature - 32) / 1.8 + 273.15);
        } else {
            return Math.round(temperature);
        }
    }

}
