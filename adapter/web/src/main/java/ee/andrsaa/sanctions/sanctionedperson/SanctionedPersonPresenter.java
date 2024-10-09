package ee.andrsaa.sanctions.sanctionedperson;

import ee.andrsaa.sanctions.sanctionedperson.resource.CheckIfSanctionedResponseResource;
import ee.andrsaa.sanctions.sanctionedperson.resource.ErrorResponseResource;
import ee.andrsaa.sanctions.sanctionedperson.resource.SaveSanctionedPersonResponseResource;
import ee.andrsaa.sanctions.usecase.sanctionedperson.CheckIfSanctioned;
import ee.andrsaa.sanctions.usecase.sanctionedperson.SaveSanctionedPersons;
import ee.andrsaa.sanctions.usecase.sanctionedperson.UploadSanctionedPersons;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class SanctionedPersonPresenter {

  public ResponseEntity<Object> present(CheckIfSanctioned.Response response) {
    return isEmpty(response.errors())
      ? ResponseEntity.ok().body(CheckIfSanctionedResponseResource.ok(response.sanctionedPersonSimilarity()))
      : ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponseResource.conflict(toErrorStrings(response.errors())));
  }

  public ResponseEntity<Object> present(UploadSanctionedPersons.Response response) {
    return isEmpty(response.errors())
      ? ResponseEntity.ok().build()
      : ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponseResource.conflict(toErrorStrings(response.errors())));
  }

  public ResponseEntity<Object> present(SaveSanctionedPersons.Response response) {
    return ResponseEntity.ok().body(new SaveSanctionedPersonResponseResource(response.sanctionedPersons()));
  }

  private <T extends Enum<T>> Set<String> toErrorStrings(Set<T> errors) {
    return errors.stream().map(Enum::name).collect(Collectors.toSet());
  }
}
