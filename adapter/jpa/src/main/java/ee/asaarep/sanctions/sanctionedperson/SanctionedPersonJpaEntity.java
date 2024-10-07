package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.Auditable;
import ee.asaarep.sanctions.domain.SanctionedPerson;
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
@Table(name = "sanctioned_person", schema = "sanctions")
public class SanctionedPersonJpaEntity extends Auditable {
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  public SanctionedPerson toDomainEntity() {
    return SanctionedPerson.builder()
        .id(id)
        .fullName(fullName)
        .build();
  }

  public static List<SanctionedPersonJpaEntity> fromDomainEntities(List<SanctionedPerson> sanctionedPersons) {
    return sanctionedPersons.stream()
        .map(SanctionedPersonJpaEntity::toEntity)
        .toList();
  }

  public SanctionedPersonJpaEntity update(SanctionedPerson sanctionedPerson) {
    return this.toBuilder()
        .fullName(sanctionedPerson.fullName())
        .build();
  }

  private static SanctionedPersonJpaEntity toEntity(SanctionedPerson sanctionedPerson) {
    return SanctionedPersonJpaEntity.builder()
        .id(sanctionedPerson.id())
        .fullName(sanctionedPerson.fullName())
        .build();
  }
}
