package ee.asaarep.sanctions.usecase.noiseword;

import ee.asaarep.sanctions.domain.noiseword.NoiseWord;
import ee.asaarep.sanctions.domain.pageable.PagedRequest;
import ee.asaarep.sanctions.domain.pageable.PagedResult;
import ee.asaarep.sanctions.usecase.noiseword.port.FindNoiseWordsPort;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindNoiseWords {
  private final FindNoiseWordsPort findNoiseWordsPort;

  public PagedResult<NoiseWord> execute(Request request) {
    log.info("Getting noise words");
    return findNoiseWordsPort.find(request);
  }

  @NoArgsConstructor
  @SuperBuilder
  public static class Request extends PagedRequest {
  }
}
