package ee.asaarep.sanctions.usecase.sanctionedperson.port;

import ee.asaarep.sanctions.domain.pageable.PagedResult;
import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPersonSimilarity;
import ee.asaarep.sanctions.usecase.sanctionedperson.CheckIfSanctioned;
import ee.asaarep.sanctions.usecase.sanctionedperson.FindSanctionedPersons;

public interface FindSanctionedPersonPort {
  PagedResult<SanctionedPerson> findSanctionedPersons(FindSanctionedPersons.Request request);

  SanctionedPersonSimilarity checkIfPersonIsSanctioned(CheckIfSanctioned.Request request);
}
