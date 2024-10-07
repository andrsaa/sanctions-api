package ee.asaarep.sanctions.usecase.sanctionedperson;

import ee.asaarep.sanctions.usecase.sanctionedperson.port.DeleteSanctionedPersonPort;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteSanctionedPersons {
  private final DeleteSanctionedPersonPort deleteSanctionedPersonPort;

  @Transactional
  public void execute(Request request) {
    log.info("Deleting sanctioned persons");
    deleteSanctionedPersonPort.delete(request);
  }

  @Getter
  @Builder(access = PRIVATE)
  public static class Request {
    private Set<UUID> sanctionedPersonsToDelete;

    public static Request of(Set<UUID> ids) {
      return Request.builder()
        .sanctionedPersonsToDelete(ids)
        .build();
    }
  }
}
