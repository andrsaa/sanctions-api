package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.sanctionedperson.resource.*;
import ee.asaarep.sanctions.usecase.sanctionedperson.*;
import ee.asaarep.sanctions.util.PageableUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/sanctioned-person")
public class SanctionedPersonController {
  private static final String TEXT_CSV_TYPE = "text/csv";

  private final FindSanctionedPersons findSanctionedPersons;
  private final SaveSanctionedPersons saveSanctionedPersons;
  private final UpdateSanctionedPersons updateSanctionedPersons;
  private final DeleteSanctionedPersons deleteSanctionedPersons;
  private final CheckIfPersonIsSanctioned checkIfPersonIsSanctioned;
  private final UploadSanctionedPersons uploadSanctionedPersons;
  private final SanctionedPersonPresenter sanctionedPersonPresenter;

  @GetMapping("/get")
  public Page<SanctionedPersonResultRow> getSanctionedPersons(@Valid @RequestBody SanctionedPersonSearchParams params, Pageable pageable) {
    return PageableUtil.present(findSanctionedPersons.execute(PageableUtil.toRequest(pageable, params.toRequest())),
      SanctionedPersonResultRow::new);
  }

  @PostMapping("/check")
  public ResponseEntity<CheckIfSanctionedResponseResource> checkSanctionedPerson(@Valid @RequestBody CheckIfSanctionedRequestResource resource) {
    return sanctionedPersonPresenter.present(checkIfPersonIsSanctioned.execute(resource.toRequest()));
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
  public ResponseEntity<Void> uploadSanctionedPersons(@RequestParam("file") MultipartFile file) throws IOException {
    log.info("Uploading sanctioned persons from CSV file with size: {} kb", Math.round(file.getSize() / 1024.0));
    if (file.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    if (!TEXT_CSV_TYPE.equals(file.getContentType())) {
      return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
    }
    uploadSanctionedPersons.execute(UploadSanctionedPersons.Request.of(file.getInputStream()));
    return ResponseEntity.ok().build();
  }
}
