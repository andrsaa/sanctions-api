package ee.asaarep.sanctions.sanctionedperson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface SanctionedPersonRepository extends JpaRepository<SanctionedPersonJpaEntity, UUID>,
  JpaSpecificationExecutor<SanctionedPersonJpaEntity> {

  @Query(value = "SELECT EXISTS(SELECT sanctions.similarity(:fullName, sp.full_name) > 0.2 " +
    "FROM sanctions.sanctioned_person sp)", nativeQuery = true)
  boolean checkIfPersonIsSanctioned(String fullName);
}
