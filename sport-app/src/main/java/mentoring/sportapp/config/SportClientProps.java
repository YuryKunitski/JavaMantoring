package mentoring.sportapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "web-client")
public final class SportClientProps {

  private String baseUrl;
}
