package ee.asaarep.sanctions.noiseword;

import ee.asaarep.sanctions.domain.noiseword.NoiseWord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@SuperBuilder(toBuilder = true)
@Table(name = "noise_word", schema = "sanctions")
public class NoiseWordJpaEntity {

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "value", nullable = false)
  private String value;

  public NoiseWord toDomainEntity() {
    return NoiseWord.builder()
      .id(id)
      .value(value)
      .build();
  }

  public static List<NoiseWordJpaEntity> fromDomainEntities(List<NoiseWord> noiseWords) {
    return noiseWords.stream()
      .map(NoiseWordJpaEntity::toEntity)
      .toList();
  }

  private static NoiseWordJpaEntity toEntity(NoiseWord noiseWord) {
    return NoiseWordJpaEntity.builder()
      .value(noiseWord.value())
      .build();
  }
}
