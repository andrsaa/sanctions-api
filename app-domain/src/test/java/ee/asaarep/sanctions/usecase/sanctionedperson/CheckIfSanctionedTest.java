package ee.asaarep.sanctions.usecase.sanctionedperson;

import ee.asaarep.sanctions.domain.noiseword.NoiseWord;
import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPersonSimilarity;
import ee.asaarep.sanctions.usecase.noiseword.port.FindNoiseWordsPort;
import ee.asaarep.sanctions.usecase.sanctionedperson.port.FindSanctionedPersonPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CheckIfSanctionedTest {

  @Mock
  private FindSanctionedPersonPort findSanctionedPersonPort;

  @Mock
  private FindNoiseWordsPort findNoiseWordsPort;

  @InjectMocks
  private CheckIfSanctioned checkIfSanctioned;

  @BeforeEach
  void setUp() {
    lenient().when(findNoiseWordsPort.findAll())
      .thenReturn(List.of(NoiseWord.builder().value("the").build(), NoiseWord.builder().value("and").build()));
  }

  @Test
  void execute_withEmptyName_shouldReturnInputIsEmptyViolation() {
    CheckIfSanctioned.Request request = CheckIfSanctioned.Request.of("", null);
    CheckIfSanctioned.Response response = checkIfSanctioned.execute(request);

    assertEquals(Set.of(CheckIfSanctioned.Violation.INPUT_IS_EMPTY), response.errors());
  }

  @Test
  void execute_withNoiseWordsOnly_shouldReturnOnlyNoiseWordsOrPunctuationMarksViolation() {
    CheckIfSanctioned.Request request = CheckIfSanctioned.Request.of("the and", null);
    CheckIfSanctioned.Response response = checkIfSanctioned.execute(request);

    assertEquals(Set.of(CheckIfSanctioned.Violation.INPUT_CONTAINS_ONLY_NOISE_WORDS_OR_PUNCTUATION_MARKS), response.errors());
  }

  @Test
  void execute_withPunctuationMarks_shouldRemovePunctuationMarks() {
    CheckIfSanctioned.Request request = CheckIfSanctioned.Request.of(", Osama.! Bin", null);
    when(findSanctionedPersonPort.checkIfPersonIsSanctioned(any()))
      .thenReturn(SanctionedPersonSimilarity.builder().isSanctioned(true).build());
    checkIfSanctioned.execute(request);

    ArgumentCaptor<CheckIfSanctioned.Request> captor = ArgumentCaptor.forClass(CheckIfSanctioned.Request.class);
    verify(findSanctionedPersonPort, times(1)).checkIfPersonIsSanctioned(captor.capture());

    CheckIfSanctioned.Request capturedRequest = captor.getValue();
    assertEquals("Osama Bin", capturedRequest.fullName);
    assertEquals(BigDecimal.valueOf(0.2), capturedRequest.similarityThreshold());
  }

  @Test
  void execute_withValidFullNameAndSanctionedPersonFound_shouldNotContainErrorsNorSetContext() {
    when(findSanctionedPersonPort.checkIfPersonIsSanctioned(any()))
      .thenReturn(SanctionedPersonSimilarity.builder()
        .isSanctioned(true)
        .fullName("Osama")
        .similarity(BigDecimal.valueOf(1))
        .build());

    CheckIfSanctioned.Request request = CheckIfSanctioned.Request.of("Osama", null);
    CheckIfSanctioned.Response response = checkIfSanctioned.execute(request);

    assertTrue(response.sanctionedPersonSimilarity().isSanctioned());
    assertNull(response.errors());
    assertNull(response.sanctionedPersonSimilarity().context());
  }

  @Test
  void execute_withValidFullNameAndSanctionedPersonNotFound_shouldSetContext() {
    when(findSanctionedPersonPort.checkIfPersonIsSanctioned(any()))
      .thenReturn(SanctionedPersonSimilarity.builder()
        .isSanctioned(false)
        .build());

    CheckIfSanctioned.Request request = CheckIfSanctioned.Request.of("Osama", BigDecimal.valueOf(0.5));
    CheckIfSanctioned.Response response = checkIfSanctioned.execute(request);

    assertFalse(response.sanctionedPersonSimilarity().isSanctioned());
    assertEquals("Consider lowering the similarity threshold (0.5).",
      response.sanctionedPersonSimilarity().context());
  }
}