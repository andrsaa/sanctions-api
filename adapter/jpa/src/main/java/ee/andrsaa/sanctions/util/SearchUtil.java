package ee.andrsaa.sanctions.util;

import ee.andrsaa.sanctions.domain.pageable.PagedRequest;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@UtilityClass
public class SearchUtil {
  public static PageRequest toPageRequest(PagedRequest pagedRequest) {
    return PageRequest.of(pagedRequest.page(), toPageSize(pagedRequest.pageSize()), toSort(pagedRequest.sort()));
  }

  private static int toPageSize(int pageSize) {
    if (pageSize > 100) {
      return 100;
    }
    return pageSize == 0 ? 10 : pageSize;
  }

  private static Sort toSort(List<ee.andrsaa.sanctions.domain.pageable.Sort> customSort) {
    if (isEmpty(customSort)) {
      return Sort.unsorted();
    }

    final Sort[] resultSort =
      {Sort.by(Sort.Direction.valueOf(String.valueOf(customSort.getFirst().direction())), customSort.getFirst().property())};
    customSort.stream().skip(1).forEach(sort ->
      resultSort[0] =
        resultSort[0].and(Sort.by(Sort.Direction.valueOf(String.valueOf(sort.direction())), sort.property())));

    return resultSort[0];
  }
}
