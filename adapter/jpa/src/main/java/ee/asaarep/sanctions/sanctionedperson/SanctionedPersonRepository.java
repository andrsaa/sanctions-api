package ee.asaarep.sanctions.sanctionedperson;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SanctionedPersonRepository extends JpaRepository<SanctionedPersonJpaEntity, UUID> {
  Page<SanctionedPersonJpaEntity> findAllBy(Pageable pageable);
}
