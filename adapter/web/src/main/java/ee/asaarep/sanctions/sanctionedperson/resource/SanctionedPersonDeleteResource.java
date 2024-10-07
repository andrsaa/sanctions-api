package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.usecase.sanctionedperson.DeleteSanctionedPersons;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

public record SanctionedPersonDeleteResource(@Size(min = 1, max = 1000) Set<UUID> sanctionedPersonsToDelete) {

  public DeleteSanctionedPersons.Request toRequest() {
    return DeleteSanctionedPersons.Request.of(sanctionedPersonsToDelete);
  }
}
