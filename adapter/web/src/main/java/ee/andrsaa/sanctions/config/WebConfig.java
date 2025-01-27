package ee.andrsaa.sanctions.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder()
      .setConnectTimeout(Duration.ofMillis(60000))
      .setReadTimeout(Duration.ofMillis(60000))
      .build();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoggingInterceptor());
  }
}
