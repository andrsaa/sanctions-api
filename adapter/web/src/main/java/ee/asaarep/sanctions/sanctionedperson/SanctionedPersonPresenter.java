package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.sanctionedperson.resource.CheckIfSanctionedResponseResource;
import ee.asaarep.sanctions.usecase.sanctionedperson.CheckIfPersonIsSanctioned;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class SanctionedPersonPresenter {

  public ResponseEntity<CheckIfSanctionedResponseResource> present(CheckIfPersonIsSanctioned.Response response) {
    return isEmpty(response.errors())
      ? ResponseEntity.ok().body(CheckIfSanctionedResponseResource.ok(response.sanctionedPersonSimilarity()))
      : ResponseEntity.status(HttpStatus.CONFLICT).body(CheckIfSanctionedResponseResource.conflict(response.errors()));
  }
}
