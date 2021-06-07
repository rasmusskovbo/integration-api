package rskovbo.integrationapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rskovbo.integrationapi.model.database.Temperature;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {

}
