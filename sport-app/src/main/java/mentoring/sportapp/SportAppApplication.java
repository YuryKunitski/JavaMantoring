package mentoring.sportapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SportAppApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SportAppApplication.class, args);
  }

}
