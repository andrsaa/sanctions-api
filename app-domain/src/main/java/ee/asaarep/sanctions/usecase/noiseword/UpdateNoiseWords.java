package ee.asaarep.sanctions.usecase.noiseword;

import ee.asaarep.sanctions.domain.noiseword.NoiseWord;
import ee.asaarep.sanctions.usecase.noiseword.port.SaveNoiseWordsPort;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
public class UpdateNoiseWords {
  private final SaveNoiseWordsPort saveNoiseWordsPort;

  @Transactional
  public void execute(Request request) {
    saveNoiseWordsPort.update(request);
  }

  @Getter
  @Accessors(fluent = true)
  @Builder(access = PRIVATE)
  public static class Request {
    private List<NoiseWord> noiseWords;

    public static Request of(List<NoiseWord> noiseWords) {
      return Request.builder()
        .noiseWords(noiseWords)
        .build();
    }
  }
}
