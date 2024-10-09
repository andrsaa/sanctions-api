package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPersonSimilarity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@Schema(name = "CheckIfSanctionedResponse")
public class CheckIfSanctionedResponseResource {
  private UUID personId;
  private boolean isSanctioned;
  private String fullName;
  private BigDecimal similarity;

  public static CheckIfSanctionedResponseResource ok(SanctionedPersonSimilarity sanctionedPersonSimilarity) {
    return CheckIfSanctionedResponseResource.builder()
      .personId(sanctionedPersonSimilarity.id())
      .isSanctioned(sanctionedPersonSimilarity.isSanctioned())
      .fullName(sanctionedPersonSimilarity.fullName())
      .similarity(sanctionedPersonSimilarity.similarity())
      .build();
  }
}
