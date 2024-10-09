package ee.andrsaa.sanctions.usecase.sanctionedperson.port;

import ee.andrsaa.sanctions.domain.pageable.PagedResult;
import ee.andrsaa.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.andrsaa.sanctions.domain.sanctionedperson.SanctionedPersonSimilarity;
import ee.andrsaa.sanctions.usecase.sanctionedperson.CheckIfSanctioned;
import ee.andrsaa.sanctions.usecase.sanctionedperson.FindSanctionedPersons;

public interface FindSanctionedPersonPort {
  PagedResult<SanctionedPerson> findSanctionedPersons(FindSanctionedPersons.Request request);

  SanctionedPersonSimilarity checkIfPersonIsSanctioned(CheckIfSanctioned.Request request);
}
