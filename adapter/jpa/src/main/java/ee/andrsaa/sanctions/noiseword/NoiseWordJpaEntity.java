package ee.andrsaa.sanctions.noiseword;

import ee.andrsaa.sanctions.Auditable;
import ee.andrsaa.sanctions.domain.noiseword.NoiseWord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class NoiseWordJpaEntity extends Auditable {

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

  public NoiseWordJpaEntity update(NoiseWord noiseWord) {
    return this.toBuilder()
      .value(noiseWord.value())
      .build();
  }

  private static NoiseWordJpaEntity toEntity(NoiseWord noiseWord) {
    return NoiseWordJpaEntity.builder()
      .value(noiseWord.value())
      .build();
  }
}
