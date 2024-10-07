package ee.asaarep.sanctions.util;

import ee.asaarep.sanctions.domain.pageable.PagedResult;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class PagedResultUtil {

  public static <I, O> PagedResult<O> toPagedResult(Page<I> page, Function<I, O> transformer) {
    List<O> rows = page.stream()
      .map(transformer)
      .collect(Collectors.toList());

    return new PagedResult<>(rows, page.getTotalElements(), page.getNumber(), page.getSize());
  }
}
