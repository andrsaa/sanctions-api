package ee.asaarep.sanctions.usecase.sanctionedperson;

import ee.asaarep.sanctions.domain.PagedResult;
import ee.asaarep.sanctions.domain.SanctionedPerson;
import ee.asaarep.sanctions.usecase.PagedRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class FindSanctionedPersons {
  private final FindSanctionedPersonPort findSanctionedPersonPort;

  @Transactional
  public PagedResult<SanctionedPerson> execute(Request request) {
    return findSanctionedPersonPort.findSanctionedPersons(request);
  }

  @NoArgsConstructor
  public static class Request extends PagedRequest {}
}
