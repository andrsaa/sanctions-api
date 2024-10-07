package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.usecase.sanctionedperson.CheckIfPersonIsSanctioned;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;

public record SanctionedPersonCheckIfSanctionedResource(@Nonnull @Size(min = 1, max = 255) String fullName) {

  public CheckIfPersonIsSanctioned.Request toRequest() {
    return CheckIfPersonIsSanctioned.Request.of(fullName);
  }
}
