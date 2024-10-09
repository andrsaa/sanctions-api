package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.sanctionedperson.resource.*;
import ee.asaarep.sanctions.usecase.sanctionedperson.*;
import ee.asaarep.sanctions.util.PageableUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "SanctionedPersonController", description = "Managing sanctioned persons")
public class SanctionedPersonController {
  private static final String TEXT_CSV_TYPE = "text/csv";

  private final FindSanctionedPersons findSanctionedPersons;
  private final SaveSanctionedPersons saveSanctionedPersons;
  private final UpdateSanctionedPersons updateSanctionedPersons;
  private final DeleteSanctionedPersons deleteSanctionedPersons;
  private final CheckIfPersonIsSanctioned checkIfPersonIsSanctioned;
  private final UploadSanctionedPersons uploadSanctionedPersons;
  private final SanctionedPersonPresenter sanctionedPersonPresenter;

  @Operation(summary = "Find sanctioned persons")
  @PostMapping("/find")
  Page<FindSanctionedPersonResponseResource> getSanctionedPersons(@Valid @RequestBody FindSanctionedPersonRequestResource searchParams, Pageable pageable) {
    return PageableUtil.present(findSanctionedPersons.execute(PageableUtil.toRequest(pageable, searchParams.toRequest())),
      FindSanctionedPersonResponseResource::new);
  }

  @Operation(summary = "Check if a person is sanctioned", description = "Check if given name exists in the database. " +
    "Default similarity threshold is 0.2.", responses = {
    @ApiResponse(responseCode = "200", description = "Successful Operation",
      content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = CheckIfSanctionedResponseResource.class))),
    @ApiResponse(responseCode = "409", description = "Validation error",
      content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = ErrorResponseResource.class))),
  })
  @PostMapping("/check")
  ResponseEntity<Object> checkSanctionedPerson(@Valid @RequestBody CheckIfSanctionedRequestResource resource) {
    return sanctionedPersonPresenter.present(checkIfPersonIsSanctioned.execute(resource.toRequest()));
  }

  @Operation(summary = "Add sanctioned persons")
  @PostMapping("/add")
  ResponseEntity<Void> addSanctionedPersons(@Valid @RequestBody SaveSanctionedPersonResource resource) {
    saveSanctionedPersons.execute(resource.toRequest());
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Update sanctioned persons")
  @PutMapping("/update")
  ResponseEntity<Void> updateSanctionedPersons(@Valid @RequestBody UpdateSanctionedPersonResource resource) {
    updateSanctionedPersons.execute(resource.toRequest());
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Remove sanctioned persons")
  @PostMapping("/remove")
  ResponseEntity<Void> removeSanctionedPersons(@Valid @RequestBody DeleteSanctionedPersonResource resource) {
    deleteSanctionedPersons.execute(resource.toRequest());
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Upload sanctioned persons from CSV file", description = "Based on [Consolidated Financial Sanctions File 1.1]" +
    "(https://data.europa.eu/data/datasets/consolidated-list-of-persons-groups-and-entities-subject-to-eu-financial-sanctions). " +
    "Requires header: 'NameAlias_WholeName'", responses = {
    @ApiResponse(responseCode = "200", description = "Successful Operation"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "409", description = "Validation error",
      content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = ErrorResponseResource.class))),
    @ApiResponse(responseCode = "415", description = "Unsupported media type, expecting text/csv content type"),
  })
  @PostMapping(value = "/upload", consumes = "multipart/form-data")
  ResponseEntity<Object> uploadSanctionedPersons(@RequestParam("file") MultipartFile file) throws IOException {
    log.info("Uploading sanctioned persons from CSV file with size: {} kb", Math.round(file.getSize() / 1024.0));
    if (file.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    if (!TEXT_CSV_TYPE.equals(file.getContentType())) {
      return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
    }
    return sanctionedPersonPresenter.present(uploadSanctionedPersons.execute(UploadSanctionedPersons.Request.of(file.getInputStream())));
  }
}
