package rskovbo.integrationapi.model.database;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(nullable = false)
    private long lastUpdated;

    @OneToMany(mappedBy = "temperature", cascade = CascadeType.ALL)
    private List<Temperature> temperatures = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    public void addTemperature(Temperature temperature) {
        this.temperatures.add(temperature);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", temperatures=" + temperatures +
                '}';
    }
}
