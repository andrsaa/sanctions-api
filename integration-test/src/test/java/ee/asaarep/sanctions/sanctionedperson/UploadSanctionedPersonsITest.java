package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.IntegrationTest;
import ee.asaarep.sanctions.usecase.sanctionedperson.UploadSanctionedPersons;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Set;

import static ee.asaarep.sanctions.usecase.sanctionedperson.UploadSanctionedPersons.Violation.READING_FILE_FAILED;
import static ee.asaarep.sanctions.usecase.sanctionedperson.UploadSanctionedPersons.Violation.WHOLE_NAME_HEADER_MISSING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class UploadSanctionedPersonsITest extends IntegrationTest {

  @Autowired
  private UploadSanctionedPersons uploadSanctionedPersons;
  @Autowired
  private SanctionedPersonRepository sanctionedPersonRepository;

  @Test
  public void execute_withNoHeaders_shouldReturnViolation() {
    try (var inputStream = getClass().getClassLoader().getResourceAsStream("upload-sanctioned-persons-no-header.csv")) {
      var request = UploadSanctionedPersons.Request.of(inputStream);
      var response = uploadSanctionedPersons.execute(request);

      assertEquals(Set.of(READING_FILE_FAILED), response.errors());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void execute_withNoWholeNameHeader_shouldReturnViolation() {
    try (var inputStream = getClass().getClassLoader().getResourceAsStream("upload-sanctioned-persons-no-whole-name-header.csv")) {
      var request = UploadSanctionedPersons.Request.of(inputStream);
      var response = uploadSanctionedPersons.execute(request);

      assertEquals(Set.of(WHOLE_NAME_HEADER_MISSING), response.errors());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void execute_withValidFile_shouldSaveSanctionedPersons() {
    var sanctionedPersonsBeforeCount = sanctionedPersonRepository.count();
    try (var inputStream = getClass().getClassLoader().getResourceAsStream("upload-sanctioned-persons-valid.csv")) {
      var request = UploadSanctionedPersons.Request.of(inputStream);
      var response = uploadSanctionedPersons.execute(request);

      assertNull(response.errors());
      var sanctionedPersonsAfterCount = sanctionedPersonRepository.count();
      assertEquals(sanctionedPersonsBeforeCount + 10, sanctionedPersonsAfterCount);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
