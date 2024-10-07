package ee.asaarep.sanctions.noiseword;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoiseWordRepository extends JpaRepository<NoiseWordJpaEntity, UUID> {
}
