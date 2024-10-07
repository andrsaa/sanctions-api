package ee.asaarep.sanctions.domain.noiseword;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Builder
@Accessors(fluent = true)
public class NoiseWord {
  private UUID id;
  private String value;
}
