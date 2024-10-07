package ee.asaarep.sanctions.usecase.sanctionedperson.port;

import ee.asaarep.sanctions.usecase.sanctionedperson.DeleteSanctionedPersons;

public interface DeleteSanctionedPersonPort {
  void delete(DeleteSanctionedPersons.Request request);
}
