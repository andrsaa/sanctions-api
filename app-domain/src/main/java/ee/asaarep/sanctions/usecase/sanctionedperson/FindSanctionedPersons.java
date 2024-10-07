package ee.asaarep.sanctions.usecase.sanctionedperson;

import ee.asaarep.sanctions.domain.pageable.PagedResult;
import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.asaarep.sanctions.domain.pageable.PagedRequest;
import ee.asaarep.sanctions.usecase.sanctionedperson.port.FindSanctionedPersonPort;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindSanctionedPersons {
  private final FindSanctionedPersonPort findSanctionedPersonPort;

  @Transactional
  public PagedResult<SanctionedPerson> execute(Request request) {
    log.info("Getting sanctioned persons");
    return findSanctionedPersonPort.findSanctionedPersons(request);
  }

  @Getter
  @Accessors(fluent = true)
  @SuperBuilder
  public static class Request extends PagedRequest {
    private String fullName;

    public static Request of(String name) {
      return Request.builder()
        .fullName(name)
        .build();
    }
  }
}
