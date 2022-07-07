package mentoring.sportapp.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentoring.sportapp.domain.Sport;
import mentoring.sportapp.domain.SportData;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class SportAPIClientImpl implements SportAPIClient {

  private final WebClient webClient;

  @Override
  public Flux<Sport> getALLSportData() {
    return webClient
        .get()
        .uri("/sports")
        .exchangeToMono(resp -> resp.bodyToMono(SportData.class))
        .doOnError(err -> log.error("Error getting response", err))
        .flatMapIterable(SportData::getData)
        .map(sportOriginData -> new Sport(sportOriginData.getId(),
            (String.valueOf(sportOriginData.getAttributes().get("name")))));
//        .doOnNext(data -> log.info("Sport data: {}", data));
  }
}
