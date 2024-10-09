package ee.asaarep.sanctions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = FlywayAutoConfiguration.class)
@PropertySource(value = "classpath:application.properties")
public class TestApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }

}
