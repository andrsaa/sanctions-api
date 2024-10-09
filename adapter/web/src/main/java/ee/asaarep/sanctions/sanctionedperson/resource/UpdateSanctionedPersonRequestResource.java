package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.asaarep.sanctions.usecase.sanctionedperson.UpdateSanctionedPersons;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

@Schema(name = "UpdateSanctionedPersonRequest")
public record UpdateSanctionedPersonRequestResource(
  @Size(min = 1, max = 1000) List<@Valid SanctionedPersonResource> sanctionedPersons) {

  public UpdateSanctionedPersons.Request toRequest() {
    return UpdateSanctionedPersons.Request.of(toDomainEntities());
  }

  private List<SanctionedPerson> toDomainEntities() {
    return sanctionedPersons.stream()
      .map(sanctionedPersonResource -> SanctionedPerson.builder()
        .id(sanctionedPersonResource.personToUpdateId())
        .fullName(sanctionedPersonResource.fullName())
        .build())
      .toList();
  }

  @Schema(name = "UpdateSanctionedPerson")
  private record SanctionedPersonResource(
    @Nonnull UUID personToUpdateId,
    @Nonnull @Size(min = 1, max = 255) String fullName
  ) {
  }
}
