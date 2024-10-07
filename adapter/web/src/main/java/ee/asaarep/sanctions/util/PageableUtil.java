package ee.asaarep.sanctions.util;

import ee.asaarep.sanctions.domain.PagedResult;
import ee.asaarep.sanctions.domain.Sort;
import ee.asaarep.sanctions.usecase.PagedRequest;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class PageableUtil {

  public static <T extends PagedRequest> T toRequest(Pageable pageable, T request) {
    request.sort(toSort(pageable));
    request.pageSize(pageable.getPageSize());
    request.page(pageable.getPageNumber());
    return request;
  }

  public static List<Sort> toSort(Pageable pageable) {
    return pageable.getSort().get()
        .map(o -> Sort.of(Sort.Direction.valueOf(o.getDirection().name()), o.getProperty()))
        .toList();
  }

  public static <I, O> Page<O> present(@NonNull PagedResult<I> rows, Function<I, O> transformer) {
    return new PageImpl<>(
        present(rows.content, transformer),
        PageRequest.of(rows.page, rows.size),
        rows.total
    );
  }

  private static <I, O> List<O> present(@NonNull List<I> rows, Function<I, O> transformer) {
    return rows.stream().map(transformer).collect(toList());
  }
}
