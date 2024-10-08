package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.domain.sanctionedperson.SanctionedPersonSimilarity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
public class SanctionedPersonSimilarityEntity {
  @Id
  private UUID id;
  private String fullName;
  private BigDecimal similarity;

  public SanctionedPersonSimilarity toDomainEntity() {
    return SanctionedPersonSimilarity.builder()
      .id(id)
      .fullName(fullName)
      .similarity(similarity)
      .isSanctioned(true)
      .build();
  }
}
