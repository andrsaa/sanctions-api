package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.asaarep.sanctions.usecase.sanctionedperson.SaveSanctionedPersons;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SanctionedPersonSaveResource(
  @Size(min = 1, max = 1000) List<SanctionedPersonResource> sanctionedPersons) {

  public SaveSanctionedPersons.Request toRequest() {
    return SaveSanctionedPersons.Request.of(toDomainEntities());
  }

  private List<SanctionedPerson> toDomainEntities() {
    return sanctionedPersons.stream()
      .map(sanctionedPersonResource -> SanctionedPerson.builder()
        .fullName(sanctionedPersonResource.fullName())
        .build())
      .toList();
  }

  private record SanctionedPersonResource(@Nonnull @Size(max = 255) String fullName) {
  }

}
