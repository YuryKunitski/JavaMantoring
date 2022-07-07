package mentoring.sportapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Slf4j
@Configuration
@EnableConfigurationProperties(SportClientProps.class)
public class SportClientConfig {

  @Bean
  public WebClient sportAPIClient(SportClientProps props) {
    return WebClient.builder()
        .baseUrl(props.getBaseUrl())
        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}

