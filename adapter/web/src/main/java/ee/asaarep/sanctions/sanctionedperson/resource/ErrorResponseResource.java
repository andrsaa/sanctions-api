package ee.asaarep.sanctions.sanctionedperson.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@Schema(name = "ErrorResponse")
public class ErrorResponseResource {
  private Set<String> errors;

  public static ErrorResponseResource conflict(Set<String> errors) {
    return ErrorResponseResource.builder()
      .errors(errors)
      .build();
  }
}
