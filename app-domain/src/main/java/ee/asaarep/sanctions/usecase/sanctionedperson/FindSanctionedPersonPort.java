package ee.asaarep.sanctions.usecase.sanctionedperson;

import ee.asaarep.sanctions.domain.PagedResult;
import ee.asaarep.sanctions.domain.SanctionedPerson;

public interface FindSanctionedPersonPort {
  PagedResult<SanctionedPerson> findSanctionedPersons(FindSanctionedPersons.Request request);
}
