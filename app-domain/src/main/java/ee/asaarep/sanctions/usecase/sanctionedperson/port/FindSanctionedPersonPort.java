package ee.asaarep.sanctions.usecase.sanctionedperson.port;

import ee.asaarep.sanctions.domain.pageable.PagedResult;
import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPersonSimilarity;
import ee.asaarep.sanctions.usecase.sanctionedperson.CheckIfPersonIsSanctioned;
import ee.asaarep.sanctions.usecase.sanctionedperson.FindSanctionedPersons;

public interface FindSanctionedPersonPort {
  PagedResult<SanctionedPerson> findSanctionedPersons(FindSanctionedPersons.Request request);

  SanctionedPersonSimilarity checkIfPersonIsSanctioned(CheckIfPersonIsSanctioned.Request request);
}
