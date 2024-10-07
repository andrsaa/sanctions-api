package ee.asaarep.sanctions.usecase.sanctionedperson;

import ee.asaarep.sanctions.domain.noiseword.NoiseWord;
import ee.asaarep.sanctions.usecase.noiseword.port.FindNoiseWordsPort;
import ee.asaarep.sanctions.usecase.sanctionedperson.port.FindSanctionedPersonPort;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckIfPersonIsSanctioned {
  private final FindSanctionedPersonPort findSanctionedPersonPort;
  private final FindNoiseWordsPort findNoiseWordsPort;

  public boolean execute(Request request) {
    log.info("Checking sanctioned person");
    return removeNoiseWords(request)
      .map(findSanctionedPersonPort::checkIfPersonIsSanctioned)
      .orElse(false);
  }

  private Optional<Request> removeNoiseWords(Request request) {
    var noiseWords = findNoiseWordsPort.findAll();
    var fullName = request.fullName();
    for (NoiseWord noiseWord : noiseWords) {
      if (isBlank(fullName)) {
        return Optional.empty();
      }
      fullName = fullName.replace(noiseWord.value(), "").trim();
    }
    return Optional.of(request);
  }

  @Setter
  @Getter
  @Builder(access = PRIVATE)
  @Accessors(fluent = true)
  public static class Request {
    public String fullName;

    public static Request of(String fullName) {
      return Request.builder().fullName(fullName).build();
    }
  }
}
