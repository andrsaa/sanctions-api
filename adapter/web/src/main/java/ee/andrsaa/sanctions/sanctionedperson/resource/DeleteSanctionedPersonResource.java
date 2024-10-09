package ee.andrsaa.sanctions.sanctionedperson.resource;

import ee.andrsaa.sanctions.usecase.sanctionedperson.DeleteSanctionedPersons;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

@Schema(name = "DeleteSanctionedPerson")
public record DeleteSanctionedPersonResource(@Size(min = 1, max = 1000) Set<UUID> sanctionedPersonsToDelete) {

  public DeleteSanctionedPersons.Request toRequest() {
    return DeleteSanctionedPersons.Request.of(sanctionedPersonsToDelete);
  }
}
