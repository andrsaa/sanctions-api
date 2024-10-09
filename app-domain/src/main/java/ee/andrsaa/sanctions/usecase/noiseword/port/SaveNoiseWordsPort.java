package ee.andrsaa.sanctions.usecase.noiseword.port;

import ee.andrsaa.sanctions.usecase.noiseword.SaveNoiseWords;
import ee.andrsaa.sanctions.usecase.noiseword.UpdateNoiseWords;

public interface SaveNoiseWordsPort {
  void save(SaveNoiseWords.Request request);

  void update(UpdateNoiseWords.Request request);
}
