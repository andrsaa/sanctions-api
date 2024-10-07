package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.domain.SanctionedPerson;
import lombok.Getter;

import java.util.UUID;

@Getter
public class SanctionedPersonResultRow {
  private final UUID id;
  private final String fullName;

  public SanctionedPersonResultRow(SanctionedPerson person) {
    this.id = person.id();
    this.fullName = person.fullName();
  }
}

