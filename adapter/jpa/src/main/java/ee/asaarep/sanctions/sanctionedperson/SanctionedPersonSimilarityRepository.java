package ee.asaarep.sanctions.sanctionedperson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface SanctionedPersonSimilarityRepository extends JpaRepository<SanctionedPersonSimilarityEntity, String> {

  @Query(value = "SELECT sub.id, sub.full_name, sub.similarity " +
    "FROM (SELECT sp.id AS id, sp.full_name AS full_name, sanctions.similarity(:fullName, sp.full_name) AS similarity " +
    "      FROM sanctions.sanctioned_person sp " +
    "      WHERE sanctions.similarity(:fullName, sp.full_name) > :similarityThreshold" +
    "      ORDER BY similarity DESC" +
    "      LIMIT 1) AS sub ", nativeQuery = true)
  Optional<SanctionedPersonSimilarityEntity> checkIfPersonIsSanctioned(String fullName, BigDecimal similarityThreshold);
}
