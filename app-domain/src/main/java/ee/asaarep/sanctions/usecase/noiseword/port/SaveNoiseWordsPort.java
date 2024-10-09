package ee.asaarep.sanctions.usecase.noiseword.port;

import ee.asaarep.sanctions.usecase.noiseword.SaveNoiseWords;
import ee.asaarep.sanctions.usecase.noiseword.UpdateNoiseWords;

public interface SaveNoiseWordsPort {
  void save(SaveNoiseWords.Request request);

  void update(UpdateNoiseWords.Request request);
}
