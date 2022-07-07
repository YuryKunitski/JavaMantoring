package mentoring.sportapp.api.func;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SportApiRouter {

  @Bean
  public RouterFunction<ServerResponse> sportsRouterFunction(SportApiHandler sportsApiHandler) {
    return nest(path("/api/v2/sports").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
        route(GET("/all"), sportsApiHandler::getAll)
            .andRoute(GET("")
                .and(queryParam("q", q -> !q.isBlank())), sportsApiHandler::findByName)
            .andRoute(POST("/{sportName}"), sportsApiHandler::create)
    );
  }

}
