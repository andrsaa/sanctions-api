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
@RequestMapping("/sanctions")
public class Controller {

  @GetMapping
  public void getSanctionedPersons() {
    log.info("Getting sanctioned persons");
  }

  @PostMapping("/check")
  public void checkSanctionedPerson() {
    log.info("Checking sanctioned person");
  }

  @PostMapping("/add")
  public void addSanctionedPerson() {
    log.info("Adding sanctioned person");
  }

  @PostMapping("/remove")
  public void removeSanctionedPerson() {
    log.info("Removing sanctioned person");
  }
}
