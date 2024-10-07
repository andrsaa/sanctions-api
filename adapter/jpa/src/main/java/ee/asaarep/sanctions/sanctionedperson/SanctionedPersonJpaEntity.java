package ee.asaarep.sanctions.sanctionedperson;

import ee.asaarep.sanctions.Auditable;
import ee.asaarep.sanctions.domain.SanctionedPerson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

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

  @Column(name = "name", nullable = false)
  private String name;

  public SanctionedPerson toDomainEntity() {
    return SanctionedPerson.builder()
        .id(id)
        .name(name)
        .build();
  }
}
