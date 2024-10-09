package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPerson;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.UUID;

@Getter
@Schema(name = "FindSanctionedPersonResponse")
public class FindSanctionedPersonResponseResource {
  private final UUID id;
  private final String fullName;

  public FindSanctionedPersonResponseResource(SanctionedPerson person) {
    this.id = person.id();
    this.fullName = person.fullName();
  }
}

