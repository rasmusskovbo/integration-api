package rskovbo.integrationapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rskovbo.integrationapi.repository.ForecastRepository;

@Service
public class ForecastService {

    private final ForecastRepository forecastRepository;

    @Autowired
    public ForecastService(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    public void test() {
        forecastRepository.getById((long) 1);
    }
}
