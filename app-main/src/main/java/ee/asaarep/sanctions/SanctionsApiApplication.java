package ee.asaarep.sanctions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SanctionsApiApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(SanctionsApiApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SanctionsApiApplication.class);
  }
}
