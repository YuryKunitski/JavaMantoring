package mentoring.sportapp.service;

import lombok.RequiredArgsConstructor;
import mentoring.sportapp.client.SportAPIClient;
import mentoring.sportapp.domain.Sport;
import mentoring.sportapp.repository.SportRepository;
import org.reactivestreams.Subscription;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SportStartUp implements SmartLifecycle {

  private final SportAPIClient apiClient;
  private final SportRepository repository;

  @Override
  public void start() {
//    if (repository.count().block() == 0) {
//      repository.insert(apiClient.getALLSportData()).blockLast();
//    }
    backpressure();
  }

  private void backpressure() {
    Flux<Sport> sportFlux = apiClient.getALLSportData().log();
    sportFlux.subscribe(new BaseSubscriber<>() {
      int consumed;
      final int limit = 20;

      @Override
      protected void hookOnSubscribe(Subscription subscription) {
        request(limit);
      }

      @Override
      protected void hookOnNext(Sport value) {
        repository.save(value).subscribe();
        consumed++;
        if (consumed == limit) {
          consumed = 0;
          request(limit);
        }
      }
    });
  }

  @Override
  public void stop() {
  }

  @Override
  public boolean isRunning() {
    return false;
  }

}
