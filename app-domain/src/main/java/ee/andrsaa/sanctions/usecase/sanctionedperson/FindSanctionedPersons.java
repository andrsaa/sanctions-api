package ee.andrsaa.sanctions.usecase.sanctionedperson;

import ee.andrsaa.sanctions.domain.pageable.PagedResult;
import ee.andrsaa.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.andrsaa.sanctions.domain.pageable.PagedRequest;
import ee.andrsaa.sanctions.usecase.sanctionedperson.port.FindSanctionedPersonPort;
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
