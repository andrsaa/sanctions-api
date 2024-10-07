package ee.asaarep.sanctions.usecase.noiseword.port;

import ee.asaarep.sanctions.domain.noiseword.NoiseWord;
import ee.asaarep.sanctions.domain.pageable.PagedResult;
import ee.asaarep.sanctions.usecase.noiseword.FindNoiseWords;

import java.util.List;

public interface FindNoiseWordsPort {
  PagedResult<NoiseWord> find(FindNoiseWords.Request request);

  List<NoiseWord> findAll();
}
