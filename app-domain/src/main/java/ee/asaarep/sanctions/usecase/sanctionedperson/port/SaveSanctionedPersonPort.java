package ee.asaarep.sanctions.usecase.sanctionedperson.port;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.asaarep.sanctions.usecase.sanctionedperson.SaveSanctionedPersons;
import ee.asaarep.sanctions.usecase.sanctionedperson.UpdateSanctionedPersons;

import java.util.List;

public interface SaveSanctionedPersonPort {
  List<SanctionedPerson> save(SaveSanctionedPersons.Request request);

  void update(UpdateSanctionedPersons.Request request);
}
