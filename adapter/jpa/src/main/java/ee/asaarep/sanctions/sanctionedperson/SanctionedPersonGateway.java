package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.domain.PagedResult;
import ee.asaarep.sanctions.domain.SanctionedPerson;
import ee.asaarep.sanctions.usecase.sanctionedperson.FindSanctionedPersonPort;
import ee.asaarep.sanctions.usecase.sanctionedperson.FindSanctionedPersons;
import ee.asaarep.sanctions.util.PagedResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static ee.asaarep.sanctions.util.SearchUtil.toPageRequest;

@Component
@RequiredArgsConstructor
public class SanctionedPersonGateway implements FindSanctionedPersonPort {
  private final SanctionedPersonRepository sanctionedPersonRepository;

  @Override
  public PagedResult<SanctionedPerson> findSanctionedPersons(FindSanctionedPersons.Request request) {
    return PagedResultUtil.toPagedResult(sanctionedPersonRepository.findAllBy(toPageRequest(request)),
        SanctionedPersonJpaEntity::toDomainEntity);
  }
}
