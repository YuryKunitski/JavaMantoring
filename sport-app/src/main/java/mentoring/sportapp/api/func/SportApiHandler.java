package mentoring.sportapp.api.func;

import java.util.Optional;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import mentoring.sportapp.domain.Sport;
import mentoring.sportapp.service.SportService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SportApiHandler {

  private final SportService service;

  public Mono<ServerResponse> getAll(ServerRequest request) {

    Flux<Sport> sportsStream = service.getAll();

    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(sportsStream, Sport.class)
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> create(ServerRequest request) {
    var name = Optional.of(request.pathVariable("sportName"))
        .filter(Predicate.not(String::isBlank));

    if (name.isEmpty()) {
      return ServerResponse.badRequest().bodyValue("Name is blank");
    }

    return service.isNameExist(name.get()).flatMap(exist ->
            exist
                ? ServerResponse.badRequest().bodyValue("Name already in use")
                : ServerResponse.created(null).body(service.createByName(name.get()), Sport.class))
        .doOnError(x -> ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> findByName(ServerRequest request) {
    var name = request.queryParam("q");

    return service.findByName(name.orElseThrow()).flatMap(sport ->
            ServerResponse.ok().bodyValue(sport))
        .switchIfEmpty(ServerResponse.badRequest().bodyValue("Cannot find sport by q"));
  }
}
