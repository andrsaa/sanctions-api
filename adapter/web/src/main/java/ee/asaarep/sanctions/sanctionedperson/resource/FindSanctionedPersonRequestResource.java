package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.usecase.sanctionedperson.FindSanctionedPersons;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(name = "FindSanctionedPersonRequest")
public record FindSanctionedPersonRequestResource(@Size(min = 1, max = 255) String fullName) {

  public FindSanctionedPersons.Request toRequest() {
    return FindSanctionedPersons.Request.of(fullName);
  }
}
