package ee.asaarep.sanctions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping
public class Landing {

  @GetMapping("/")
  public String landing() {
    log.info("Landing");
    return "Landing";
  }

  @PostMapping("/login")
  public String login() {
    log.info("login");
    return "login";
  }
}
