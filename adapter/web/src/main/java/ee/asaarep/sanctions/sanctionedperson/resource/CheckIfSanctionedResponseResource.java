package ee.asaarep.sanctions.sanctionedperson.resource;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPersonSimilarity;
import ee.asaarep.sanctions.usecase.sanctionedperson.CheckIfPersonIsSanctioned;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
public class CheckIfSanctionedResponseResource {
  private UUID personId;
  private boolean isSanctioned;
  private String fullName;
  private BigDecimal similarity;
  private Set<String> errors;

  public static CheckIfSanctionedResponseResource ok(SanctionedPersonSimilarity sanctionedPersonSimilarity) {
    return CheckIfSanctionedResponseResource.builder()
      .personId(sanctionedPersonSimilarity.id())
      .isSanctioned(sanctionedPersonSimilarity.isSanctioned())
      .fullName(sanctionedPersonSimilarity.fullName())
      .similarity(sanctionedPersonSimilarity.similarity())
      .build();
  }

  public static CheckIfSanctionedResponseResource conflict(Set<CheckIfPersonIsSanctioned.Violation> errors) {
    return CheckIfSanctionedResponseResource.builder()
      .errors(errors.stream()
        .map(CheckIfPersonIsSanctioned.Violation::name)
        .collect(Collectors.toSet()))
      .build();
  }
}
