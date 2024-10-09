package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.usecase.sanctionedperson.CheckIfPersonIsSanctioned;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(name = "CheckIfSanctionedRequest")
public record CheckIfSanctionedRequestResource(@Nonnull @Size(min = 1, max = 255) String fullName,
                                               @Min(0) @Max(1) BigDecimal similarityThreshold) {

  public CheckIfPersonIsSanctioned.Request toRequest() {
    return CheckIfPersonIsSanctioned.Request.of(fullName, similarityThreshold);
  }
}
