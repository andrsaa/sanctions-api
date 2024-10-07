package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.usecase.sanctionedperson.FindSanctionedPersons;
import jakarta.validation.constraints.Size;

public record SanctionedPersonSearchParams(@Size(min = 1, max = 255) String fullName) {

  public FindSanctionedPersons.Request toRequest() {
    return FindSanctionedPersons.Request.of(fullName);
  }
}
