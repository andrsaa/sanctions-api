package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.sanctionedperson.resource.*;
import ee.asaarep.sanctions.usecase.sanctionedperson.DeleteSanctionedPersons;
import ee.asaarep.sanctions.usecase.sanctionedperson.SaveSanctionedPersons;
import ee.asaarep.sanctions.usecase.sanctionedperson.UpdateSanctionedPersons;
import ee.asaarep.sanctions.util.PageableUtil;
import ee.asaarep.sanctions.usecase.sanctionedperson.FindSanctionedPersons;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping
public class SanctionedPersonController {
  private final FindSanctionedPersons findSanctionedPersons;
  private final SaveSanctionedPersons saveSanctionedPersons;
  private final UpdateSanctionedPersons updateSanctionedPersons;
  private final DeleteSanctionedPersons deleteSanctionedPersons;

  @GetMapping("/get")
  public Page<SanctionedPersonResultRow> getSanctionedPersons(@Valid @RequestBody SanctionedPersonSearchParams params, Pageable pageable) {
    return PageableUtil.present(findSanctionedPersons.execute(PageableUtil.toRequest(pageable, params.toRequest())),
        SanctionedPersonResultRow::new);
  }

  @PostMapping("/check")
  public void checkSanctionedPerson() {
    log.info("Checking sanctioned person");
  }

  @PostMapping("/add-person")
  public ResponseEntity<Void> addSanctionedPersons(@Valid @RequestBody SanctionedPersonSaveResource resource) {
    saveSanctionedPersons.execute(resource.toRequest());
    return ResponseEntity.ok().build();
  }

  @PutMapping("/update-person")
  public ResponseEntity<Void> updateSanctionedPersons(@Valid @RequestBody SanctionedPersonUpdateResource resource) {
    updateSanctionedPersons.execute(resource.toRequest());
    return ResponseEntity.ok().build();
  }

  @PostMapping("/remove-person")
  public ResponseEntity<Void> removeSanctionedPerson(@Valid @RequestBody SanctionedPersonDeleteResource resource) {
    deleteSanctionedPersons.execute(resource.toRequest());
    return ResponseEntity.ok().build();
  }

  @PostMapping("/upload-person")
  public void uploadSanctionedPersons() {
    log.info("Adding sanctioned person");
  }
}
