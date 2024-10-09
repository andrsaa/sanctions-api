package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPersonSimilarity;

import java.math.BigDecimal;
import java.util.UUID;

public interface SanctionedPersonSimilarityProjection {
  UUID getId();
  String getFullName();
  BigDecimal getSimilarity();

  default SanctionedPersonSimilarity toDomainEntity() {
    return SanctionedPersonSimilarity.builder()
      .id(getId())
      .fullName(getFullName())
      .similarity(getSimilarity())
      .isSanctioned(true)
      .build();
  }
}
