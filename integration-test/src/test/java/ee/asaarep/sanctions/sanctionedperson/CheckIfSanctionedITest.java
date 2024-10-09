package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.IntegrationTest;
import ee.asaarep.sanctions.usecase.sanctionedperson.CheckIfSanctioned;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class CheckIfSanctionedITest extends IntegrationTest {

  @Autowired
  private CheckIfSanctioned checkIfSanctioned;

  @Test
  void execute_withEmptyFullName_shouldReturnInputViolation() {
    var request = CheckIfSanctioned.Request.of("", null);
    var response = checkIfSanctioned.execute(request);

    assertEquals(Set.of(CheckIfSanctioned.Violation.INPUT_IS_EMPTY), response.errors());
  }

  @Test
  void execute_withNoiseWordsOnly_shouldReturnInputViolation() {
    var request = CheckIfSanctioned.Request.of("the and", null);
    var response = checkIfSanctioned.execute(request);

    assertEquals(Set.of(CheckIfSanctioned.Violation.INPUT_CONTAINS_ONLY_NOISE_WORDS_OR_PUNCTUATION_MARKS), response.errors());
  }

  @Test
  void execute_withPunctuationMarks_shouldFindSanctionedPerson() {
    var request = CheckIfSanctioned.Request.of(", Osama.! Bin", null);
    var response = checkIfSanctioned.execute(request);

    assertTrue(response.sanctionedPersonSimilarity().isSanctioned());
  }

  @Test
  void execute_withValidFullName_shouldFindSanctionedPerson() {
    var request = CheckIfSanctioned.Request.of("Osama", null);
    var response = checkIfSanctioned.execute(request);

    assertTrue(response.sanctionedPersonSimilarity().isSanctioned());
    assertNull(response.errors());
    assertNull(response.sanctionedPersonSimilarity().context());
  }

  @Test
  void execute_withValidFullName_shouldNotFindSanctionedPersonAndSetContext() {
    var request = CheckIfSanctioned.Request.of("Osama", BigDecimal.valueOf(0.5));
    var response = checkIfSanctioned.execute(request);

    assertFalse(response.sanctionedPersonSimilarity().isSanctioned());
    assertEquals("Consider lowering the similarity threshold (0.5).",
      response.sanctionedPersonSimilarity().context());
  }

  @Test
  void execute_withValidFullNames_shouldReturnIsSanctioned() {
    var inputs = List.of("Osama Laden", "Osama Bin Laden", "Bin Laden, Osama", "Laden Osama Bin",
      "to the osama bin laden", "osama and bin laden", "Ben Osama Ladn");

    inputs.forEach(input -> {
      var request = CheckIfSanctioned.Request.of(input, null);
      var response = checkIfSanctioned.execute(request);

      assertTrue(response.sanctionedPersonSimilarity().isSanctioned(), "Failed for input: " + input);
      assertNull(response.errors());
      assertNull(response.sanctionedPersonSimilarity().context());
    });
  }

  @Test
  void execute_withValidFullName_shouldNotFindMatchWithDefaultThreshold() {
    var request = CheckIfSanctioned.Request.of("Ladn the Asoma", null);
    var response = checkIfSanctioned.execute(request);

    assertFalse(response.sanctionedPersonSimilarity().isSanctioned());
    assertNull(response.errors());
    assertEquals("Consider lowering the similarity threshold (0.2).",
      response.sanctionedPersonSimilarity().context());

    request = CheckIfSanctioned.Request.of("Ladn the Asoma", BigDecimal.valueOf(0.1));
    response = checkIfSanctioned.execute(request);

    assertTrue(response.sanctionedPersonSimilarity().isSanctioned());
    assertNull(response.errors());
    assertNull(response.sanctionedPersonSimilarity().context());
  }
}
