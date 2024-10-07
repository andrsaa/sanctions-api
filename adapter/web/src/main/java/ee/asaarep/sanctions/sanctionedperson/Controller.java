package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.util.PageableUtil;
import ee.asaarep.sanctions.sanctionedperson.resource.SanctionedPersonResultRow;
import ee.asaarep.sanctions.usecase.sanctionedperson.FindSanctionedPersons;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/sanctions")
public class Controller {
  private final FindSanctionedPersons findSanctionedPersons;

  @GetMapping
  public Page<SanctionedPersonResultRow> getSanctionedPersons(Pageable pageable) {
    log.info("Getting sanctioned persons");
    return PageableUtil.present(findSanctionedPersons.execute(PageableUtil.toRequest(pageable, new FindSanctionedPersons.Request())),
        SanctionedPersonResultRow::new);
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
