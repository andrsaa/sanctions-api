package ee.andrsaa.sanctions.domain.pageable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
public abstract class PagedRequest {
  private int page;
  private int pageSize;
  private List<Sort> sort;
}
