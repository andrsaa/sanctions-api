package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.domain.SanctionedPerson;

public class SanctionedPersonResultRow {
  private final String name;

  public SanctionedPersonResultRow(SanctionedPerson person) {
    this.name = person.name();
  }
}

