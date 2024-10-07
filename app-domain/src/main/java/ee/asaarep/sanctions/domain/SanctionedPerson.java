package ee.asaarep.sanctions.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Builder
@Accessors(fluent = true)
public class SanctionedPerson {
  private final UUID id;
  private final String name;
}
