package rskovbo.integrationapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rskovbo.integrationapi.model.rest.Forecast;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {
}
