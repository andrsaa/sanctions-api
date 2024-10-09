package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPerson;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Schema(name = "SaveSanctionedPersonResponse")
public class SaveSanctionedPersonResponseResource {
  private final List<SanctionedPersonResource> sanctionedPersons;

  public SaveSanctionedPersonResponseResource(List<SanctionedPerson> sanctionedPersons) {
    this.sanctionedPersons = sanctionedPersons.stream()
      .map(sanctionedPerson -> new SanctionedPersonResource(sanctionedPerson.id(), sanctionedPerson.fullName()))
      .toList();
  }

  @Schema(name = "SanctionedPerson")
  private record SanctionedPersonResource(UUID id, String fullName) {}
}
