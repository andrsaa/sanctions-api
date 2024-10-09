package ee.andrsaa.sanctions.usecase.noiseword.port;

import ee.andrsaa.sanctions.usecase.noiseword.DeleteNoiseWords;

public interface DeleteNoiseWordsPort {

  void delete(DeleteNoiseWords.Request request);
}
