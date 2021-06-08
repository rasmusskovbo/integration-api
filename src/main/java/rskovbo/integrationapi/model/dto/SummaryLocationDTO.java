package rskovbo.integrationapi.model.dto;

public class SummaryLocationDTO {

    private String name;
    private double temperature;

    public SummaryLocationDTO(String name, double temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
