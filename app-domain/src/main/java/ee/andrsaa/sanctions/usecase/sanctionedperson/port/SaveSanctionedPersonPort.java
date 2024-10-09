package ee.andrsaa.sanctions.usecase.sanctionedperson.port;

import ee.andrsaa.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.andrsaa.sanctions.usecase.sanctionedperson.SaveSanctionedPersons;
import ee.andrsaa.sanctions.usecase.sanctionedperson.UpdateSanctionedPersons;

import java.util.List;

public interface SaveSanctionedPersonPort {
  List<SanctionedPerson> save(SaveSanctionedPersons.Request request);

  void update(UpdateSanctionedPersons.Request request);
}
