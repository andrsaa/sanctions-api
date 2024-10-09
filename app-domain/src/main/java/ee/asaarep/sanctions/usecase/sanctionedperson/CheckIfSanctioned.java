package ee.asaarep.sanctions.usecase.sanctionedperson;

import ee.asaarep.sanctions.domain.noiseword.NoiseWord;
import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPersonSimilarity;
import ee.asaarep.sanctions.usecase.noiseword.port.FindNoiseWordsPort;
import ee.asaarep.sanctions.usecase.sanctionedperson.port.FindSanctionedPersonPort;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckIfSanctioned {
  private static final double DEFAULT_SIMILARITY_THRESHOLD = 0.2;
  private static final BigDecimal ADD_CONTEXT_SIMILARITY_THRESHOLD = BigDecimal.valueOf(0.4);
  private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("\\p{Punct}");

  private final FindSanctionedPersonPort findSanctionedPersonPort;
  private final FindNoiseWordsPort findNoiseWordsPort;

  @Transactional
  public Response execute(Request request) {
    setSimilarityThreshold(request);
    log.debug("Sanctioned person check with full name: {} and similarity threshold: {}", request.fullName, request.similarityThreshold);

    if (isBlank(request.fullName)) {
      return Response.error(Set.of(Violation.INPUT_IS_EMPTY));
    }
    var normalizedFullName = normalize(request.fullName);
    if (normalizedFullName.isEmpty()) {
      return Response.error(Set.of(Violation.INPUT_CONTAINS_ONLY_NOISE_WORDS_OR_PUNCTUATION_MARKS));
    }
    request.fullName(normalizedFullName.get());
    return Response.ok(addContext(findSanctionedPersonPort.checkIfPersonIsSanctioned(request), request));
  }

  private Optional<String> normalize(String fullName) {
    return removePunctuationMarks(fullName)
      .flatMap(this::removeNoiseWords);
  }

  private void setSimilarityThreshold(Request request) {
    if (isNull(request.similarityThreshold())) {
      request.similarityThreshold(BigDecimal.valueOf(DEFAULT_SIMILARITY_THRESHOLD));
    }
  }

  private Optional<String> removePunctuationMarks(String fullName) {
    fullName = PUNCTUATION_PATTERN.matcher(fullName).replaceAll("").trim();
    if (isBlank(fullName)) {
      return Optional.empty();
    }
    return Optional.of(fullName);
  }

  private Optional<String> removeNoiseWords(String fullName) {
    var noiseWords = findNoiseWordsPort.findAll();
    for (NoiseWord noiseWord : noiseWords) {
      fullName = fullName.replaceAll("\\b" + noiseWord.value() + "\\b", "").trim();

      if (isBlank(fullName)) {
        return Optional.empty();
      }
    }
    fullName = fullName.replaceAll("\\s{2,}", " ");
    return Optional.of(fullName);
  }

  private SanctionedPersonSimilarity addContext(SanctionedPersonSimilarity sanctionedPersonSimilarity, Request request) {
    if (ADD_CONTEXT_SIMILARITY_THRESHOLD.compareTo(request.similarityThreshold) < 0 && !sanctionedPersonSimilarity.isSanctioned()) {
      return sanctionedPersonSimilarity.withContext("Consider lowering the similarity threshold (" +
        request.similarityThreshold.toString() +") or opting for a more specific name.");
    }
    return sanctionedPersonSimilarity;
  }

  @Setter
  @Getter
  @Builder(access = PRIVATE)
  @Accessors(fluent = true)
  public static class Request {
    public String fullName;
    public BigDecimal similarityThreshold;

    public static Request of(String fullName, BigDecimal similarityThreshold) {
      return Request.builder()
        .fullName(fullName)
        .similarityThreshold(similarityThreshold)
        .build();
    }
  }

  @Getter
  @Builder(access = PRIVATE)
  @Accessors(fluent = true)
  public static class Response {
    private SanctionedPersonSimilarity sanctionedPersonSimilarity;
    private Set<Violation> errors;

    public static Response ok(SanctionedPersonSimilarity sanctionedPersonSimilarity) {
      return Response.builder()
        .sanctionedPersonSimilarity(sanctionedPersonSimilarity)
        .build();
    }

    public static Response error(Set<Violation> errors) {
      return Response.builder()
        .errors(errors)
        .build();
    }
  }

  public enum Violation {
    INPUT_IS_EMPTY,
    INPUT_CONTAINS_ONLY_NOISE_WORDS_OR_PUNCTUATION_MARKS
  }
}
