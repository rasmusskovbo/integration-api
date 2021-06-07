package rskovbo.integrationapi.model.database;

import javax.persistence.*;

@Table
@Entity(name = "temperature")
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @Column(nullable = false)
    private long timestamp;

    @Column(nullable = false)
    private double temperature;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", temperature=" + temperature +
                ", location=" + location +
                '}';
    }
}
