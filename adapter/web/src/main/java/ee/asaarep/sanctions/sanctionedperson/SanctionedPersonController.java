package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.sanctionedperson.resource.*;
import ee.asaarep.sanctions.usecase.sanctionedperson.*;
import ee.asaarep.sanctions.util.PageableUtil;
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
@RequestMapping("/sanctioned-person")
public class SanctionedPersonController {
  private final FindSanctionedPersons findSanctionedPersons;
  private final SaveSanctionedPersons saveSanctionedPersons;
  private final UpdateSanctionedPersons updateSanctionedPersons;
  private final DeleteSanctionedPersons deleteSanctionedPersons;
  private final CheckIfPersonIsSanctioned checkIfPersonIsSanctioned;

  @GetMapping("/get")
  public Page<SanctionedPersonResultRow> getSanctionedPersons(@Valid @RequestBody SanctionedPersonSearchParams params, Pageable pageable) {
    return PageableUtil.present(findSanctionedPersons.execute(PageableUtil.toRequest(pageable, params.toRequest())),
      SanctionedPersonResultRow::new);
  }

  @PostMapping("/check")
  public ResponseEntity<Boolean> checkSanctionedPerson(@Valid @RequestBody SanctionedPersonCheckIfSanctionedResource resource) {
    return ResponseEntity.ok(checkIfPersonIsSanctioned.execute(resource.toRequest()));
  }

  @PostMapping("/add")
  public ResponseEntity<Void> addSanctionedPersons(@Valid @RequestBody SanctionedPersonSaveResource resource) {
    saveSanctionedPersons.execute(resource.toRequest());
    return ResponseEntity.ok().build();
  }

  @PutMapping("/update")
  public ResponseEntity<Void> updateSanctionedPersons(@Valid @RequestBody SanctionedPersonUpdateResource resource) {
    updateSanctionedPersons.execute(resource.toRequest());
    return ResponseEntity.ok().build();
  }

  @PostMapping("/remove")
  public ResponseEntity<Void> removeSanctionedPerson(@Valid @RequestBody SanctionedPersonDeleteResource resource) {
    deleteSanctionedPersons.execute(resource.toRequest());
    return ResponseEntity.ok().build();
  }

  @PostMapping("/upload")
  public void uploadSanctionedPersons() {
    log.info("Adding sanctioned person");
  }
}
