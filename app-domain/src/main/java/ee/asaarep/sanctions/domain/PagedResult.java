package ee.asaarep.sanctions.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PagedResult<T> {
  public List<T> content;
  public long total;
  public int page;
  public int size;
}
