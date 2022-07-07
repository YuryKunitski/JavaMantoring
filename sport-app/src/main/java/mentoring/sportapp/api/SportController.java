package mentoring.sportapp.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentoring.sportapp.domain.Sport;
import mentoring.sportapp.service.SportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/sport")
@RequiredArgsConstructor
public class SportController {

  private final SportService service;

  @GetMapping("/all")
  public Flux<Sport> getAll() {
    return service.getAll();
  }

  @PostMapping("/{sportName}")
  public Mono<ResponseEntity<Object>> create(@PathVariable String sportName) {
    return service.isNameExist(sportName)
        .map(exist -> {
          if (exist) {
            return ResponseEntity.badRequest()
                .body("Name already exist.");
          }
          return ResponseEntity.ok().body(service.createByName(sportName));
        });
  }

  @GetMapping
  public Mono<ResponseEntity<Sport>> findByName(@RequestParam("q") String name) {
    return service.findByName(name)
        .map(ResponseEntity::ok)
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteById(@PathVariable String id) {
    return service.deleteById(id);
  }
}
