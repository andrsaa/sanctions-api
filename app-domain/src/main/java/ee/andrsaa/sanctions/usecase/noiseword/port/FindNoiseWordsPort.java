package ee.andrsaa.sanctions.usecase.noiseword.port;

import ee.andrsaa.sanctions.domain.noiseword.NoiseWord;
import ee.andrsaa.sanctions.domain.pageable.PagedResult;
import ee.andrsaa.sanctions.usecase.noiseword.FindNoiseWords;

import java.util.List;

public interface FindNoiseWordsPort {
  PagedResult<NoiseWord> find(FindNoiseWords.Request request);

  List<NoiseWord> findAll();
}
