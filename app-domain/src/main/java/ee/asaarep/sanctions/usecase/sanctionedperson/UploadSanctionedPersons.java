package ee.asaarep.sanctions.usecase.sanctionedperson;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.asaarep.sanctions.usecase.sanctionedperson.port.SaveSanctionedPersonPort;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
@RequiredArgsConstructor
@Slf4j
public class UploadSanctionedPersons {
  private static final String DELIMITER = ";";
  private static final String WHOLE_NAME_HEADER = "NameAlias_WholeName";

  private final SaveSanctionedPersonPort saveSanctionedPersonPort;

  @Transactional
  public Response execute(Request request) {
    log.debug("Reading sanctioned persons");
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.inputStream));
         CSVParser csvParser = new CSVParser(reader, getCSVFormat())) {
      if (!csvParser.getHeaderMap().containsKey(WHOLE_NAME_HEADER)) {
        log.error("Required header: '{}' is missing", WHOLE_NAME_HEADER);
        return Response.error(Set.of(Violation.WHOLE_NAME_HEADER_MISSING));
      }
      List<SanctionedPerson> sanctionedPersons = new ArrayList<>();
      for (CSVRecord record : csvParser) {
        if (isBlank(record.get(WHOLE_NAME_HEADER))) {
          continue;
        }
        SanctionedPerson sanctionedPerson = SanctionedPerson.builder().fullName(record.get(WHOLE_NAME_HEADER)).build();
        sanctionedPersons.add(sanctionedPerson);
      }
      log.debug("Saving sanctioned persons");
      saveSanctionedPersonPort.save(SaveSanctionedPersons.Request.of(sanctionedPersons));
      return Response.ok();
    } catch (IOException e) {
      log.error("Error reading CSV file", e);
      return Response.error(Set.of(Violation.READING_FILE_FAILED));
    }
  }

  private CSVFormat getCSVFormat() {
    return CSVFormat.DEFAULT.builder()
      .setDelimiter(DELIMITER)
      .setHeader()
      .setSkipHeaderRecord(true)
      .build();
  }

  @Getter
  @Builder(access = PRIVATE)
  public static class Request {
    private final InputStream inputStream;

    public static Request of(InputStream inputStream) {
      return builder()
        .inputStream(inputStream)
        .build();
    }
  }

  @Getter
  @Builder(access = PRIVATE)
  @Accessors(fluent = true)
  public static class Response {
    private Set<Violation> errors;

    public static Response ok() {
      return builder().build();
    }

    public static Response error(Set<Violation> violations) {
      return builder()
        .errors(violations)
        .build();
    }
  }

  public enum Violation {
    WHOLE_NAME_HEADER_MISSING,
    READING_FILE_FAILED
  }
}
