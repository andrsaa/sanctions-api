package ee.asaarep.sanctions.sanctionedperson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SanctionedPersonRepository extends JpaRepository<SanctionedPersonJpaEntity, UUID>,
  JpaSpecificationExecutor<SanctionedPersonJpaEntity> {
}
