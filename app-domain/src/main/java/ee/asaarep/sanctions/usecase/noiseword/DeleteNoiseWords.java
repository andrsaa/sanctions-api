package ee.asaarep.sanctions.usecase.noiseword;

import ee.asaarep.sanctions.usecase.noiseword.port.DeleteNoiseWordsPort;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteNoiseWords {
  private final DeleteNoiseWordsPort deleteNoiseWordsPort;

  @Transactional
  public void execute(Request request) {
    log.debug("Deleting noise words with ids: {}", request.noiseWordsToDelete);
    deleteNoiseWordsPort.delete(request);
  }

  @Getter
  @Builder(access = PRIVATE)
  @Accessors(fluent = true)
  public static class Request {
    private Set<UUID> noiseWordsToDelete;

    public static Request of(Set<UUID> ids) {
      return Request.builder()
        .noiseWordsToDelete(ids)
        .build();
    }
  }
}
