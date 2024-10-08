package ee.asaarep.sanctions.usecase.sanctionedperson;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.asaarep.sanctions.usecase.sanctionedperson.port.SaveSanctionedPersonPort;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateSanctionedPersons {
  private final SaveSanctionedPersonPort saveSanctionedPersonPort;

  public void execute(Request request) {
    log.debug("Updating sanctioned persons");
    saveSanctionedPersonPort.update(request);
  }

  @Getter
  @Accessors(fluent = true)
  @Builder(access = PRIVATE)
  public static class Request {
    private List<SanctionedPerson> sanctionedPersons;

    public static Request of(List<SanctionedPerson> sanctionedPersons) {
      return Request.builder()
        .sanctionedPersons(sanctionedPersons)
        .build();
    }
  }
}
