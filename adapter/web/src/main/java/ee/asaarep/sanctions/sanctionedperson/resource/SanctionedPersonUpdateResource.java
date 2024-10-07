package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.asaarep.sanctions.usecase.sanctionedperson.UpdateSanctionedPersons;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record SanctionedPersonUpdateResource(
  @Size(min = 1, max = 1000) List<SanctionedPersonResource> sanctionedPersons) {

  public UpdateSanctionedPersons.Request toRequest() {
    return UpdateSanctionedPersons.Request.of(toDomainEntities());
  }

  private List<SanctionedPerson> toDomainEntities() {
    return sanctionedPersons.stream()
      .map(sanctionedPersonResource -> SanctionedPerson.builder()
        .id(sanctionedPersonResource.id())
        .fullName(sanctionedPersonResource.fullName())
        .build())
      .toList();
  }

  private record SanctionedPersonResource(
    @Nonnull UUID id,
    @Nonnull @Size(max = 255) String fullName
  ) {
  }
}
