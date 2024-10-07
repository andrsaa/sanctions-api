package ee.asaarep.sanctions.usecase.noiseword.port;

import ee.asaarep.sanctions.usecase.noiseword.SaveNoiseWords;

public interface SaveNoiseWordsPort {
  void save(SaveNoiseWords.Request request);
}
