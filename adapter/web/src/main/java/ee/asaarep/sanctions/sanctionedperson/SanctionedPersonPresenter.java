package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.sanctionedperson.resource.CheckIfSanctionedResponseResource;
import ee.asaarep.sanctions.sanctionedperson.resource.ErrorResponseResource;
import ee.asaarep.sanctions.usecase.sanctionedperson.CheckIfPersonIsSanctioned;
import ee.asaarep.sanctions.usecase.sanctionedperson.UploadSanctionedPersons;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class SanctionedPersonPresenter {

  public ResponseEntity<Object> present(CheckIfPersonIsSanctioned.Response response) {
    return isEmpty(response.errors())
      ? ResponseEntity.ok().body(CheckIfSanctionedResponseResource.ok(response.sanctionedPersonSimilarity()))
      : ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponseResource.conflict(toErrorStrings(response.errors())));
  }

  public ResponseEntity<Object> present(UploadSanctionedPersons.Response response) {
    return isEmpty(response.errors())
      ? ResponseEntity.ok().build()
      : ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponseResource.conflict(toErrorStrings(response.errors())));
  }

  private <T extends Enum<T>> Set<String> toErrorStrings(Set<T> errors) {
    return errors.stream().map(Enum::name).collect(Collectors.toSet());
  }
}
