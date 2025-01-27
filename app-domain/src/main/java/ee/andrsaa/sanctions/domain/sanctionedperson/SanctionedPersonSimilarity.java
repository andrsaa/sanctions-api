package ee.andrsaa.sanctions.domain.sanctionedperson;

import lombok.Builder;
import lombok.Getter;
import lombok.With;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@Accessors(fluent = true)
public class SanctionedPersonSimilarity {
  private UUID id;
  private String fullName;
  private BigDecimal similarity;
  private boolean isSanctioned;
  @With
  private String context;

  public static SanctionedPersonSimilarity notSanctioned() {
    return SanctionedPersonSimilarity.builder()
      .isSanctioned(false)
      .build();
  }
}
