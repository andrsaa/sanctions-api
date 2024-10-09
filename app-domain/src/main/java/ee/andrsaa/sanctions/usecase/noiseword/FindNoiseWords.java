package ee.andrsaa.sanctions.usecase.noiseword;

import ee.andrsaa.sanctions.domain.noiseword.NoiseWord;
import ee.andrsaa.sanctions.domain.pageable.PagedRequest;
import ee.andrsaa.sanctions.domain.pageable.PagedResult;
import ee.andrsaa.sanctions.usecase.noiseword.port.FindNoiseWordsPort;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindNoiseWords {
  private final FindNoiseWordsPort findNoiseWordsPort;

  public PagedResult<NoiseWord> execute(Request request) {
    return findNoiseWordsPort.find(request);
  }

  @NoArgsConstructor
  @SuperBuilder
  public static class Request extends PagedRequest {}
}
