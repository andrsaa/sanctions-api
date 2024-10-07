package ee.asaarep.sanctions.usecase.sanctionedperson.port;

import ee.asaarep.sanctions.domain.PagedResult;
import ee.asaarep.sanctions.domain.SanctionedPerson;
import ee.asaarep.sanctions.usecase.sanctionedperson.FindSanctionedPersons;

public interface FindSanctionedPersonPort {
  PagedResult<SanctionedPerson> findSanctionedPersons(FindSanctionedPersons.Request request);
}
