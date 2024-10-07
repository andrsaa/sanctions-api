package ee.asaarep.sanctions.usecase.noiseword;

import ee.asaarep.sanctions.domain.noiseword.NoiseWord;
import ee.asaarep.sanctions.usecase.noiseword.port.SaveNoiseWordsPort;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveNoiseWords {
  private final SaveNoiseWordsPort saveNoiseWordsPort;

  public void execute(Request request) {
    log.info("Saving noise words");
    saveNoiseWordsPort.save(request);
  }

  @Getter
  @Builder(access = PRIVATE)
  @Accessors(fluent = true)
  public static class Request {
    private List<NoiseWord> noiseWords;

    public static Request of(Set<String> noiseWords) {
      return Request.builder()
        .noiseWords(toNoiseWords(noiseWords))
        .build();
    }

    private static List<NoiseWord> toNoiseWords(Set<String> noiseWords) {
      return noiseWords.stream()
        .map(Request::toNoiseWord)
        .toList();
    }

    private static NoiseWord toNoiseWord(String noiseWord) {
      return NoiseWord.builder()
        .value(noiseWord)
        .build();
    }
  }
}
