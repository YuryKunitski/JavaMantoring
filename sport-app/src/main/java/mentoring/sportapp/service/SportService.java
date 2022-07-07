package mentoring.sportapp.service;

import lombok.RequiredArgsConstructor;
import mentoring.sportapp.domain.Sport;
import mentoring.sportapp.repository.SportRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SportService {

  private final SportRepository repository;

  public Mono<Boolean> isNameExist(String name) {
    return repository.existsByName(name);
  }

  public Mono<Sport> createByName(String sportName) {
    Sport sport = new Sport();
    sport.setName(sportName);
    return repository.insert(sport);
  }

  public Mono<Sport> findByName(String name) {
    return repository.findByName(name);
  }
}
