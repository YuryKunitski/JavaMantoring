package mentoring.sportapp.client;

import mentoring.sportapp.domain.Sport;
import reactor.core.publisher.Flux;

public interface SportAPIClient {

  Flux<Sport> getALLSportData();
}
