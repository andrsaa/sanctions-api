package ee.asaarep.sanctions.domain.pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor(staticName = "of")
@Accessors(fluent = true)
public class Sort {
  private Direction direction;
  private String property;

  public enum Direction {
    ASC, DESC
  }
}
