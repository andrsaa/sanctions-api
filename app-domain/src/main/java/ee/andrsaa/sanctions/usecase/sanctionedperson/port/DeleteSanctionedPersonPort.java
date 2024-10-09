package ee.andrsaa.sanctions.usecase.sanctionedperson.port;

import ee.andrsaa.sanctions.usecase.sanctionedperson.DeleteSanctionedPersons;

public interface DeleteSanctionedPersonPort {
  void delete(DeleteSanctionedPersons.Request request);
}
