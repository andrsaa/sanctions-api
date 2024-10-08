package ee.asaarep.sanctions.usecase.sanctionedperson.port;

import ee.asaarep.sanctions.usecase.sanctionedperson.SaveSanctionedPersons;
import ee.asaarep.sanctions.usecase.sanctionedperson.UpdateSanctionedPersons;

public interface SaveSanctionedPersonPort {
  void save(SaveSanctionedPersons.Request request);

  void update(UpdateSanctionedPersons.Request request);
}
