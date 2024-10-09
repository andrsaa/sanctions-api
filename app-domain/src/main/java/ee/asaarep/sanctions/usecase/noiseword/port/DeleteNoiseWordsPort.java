package ee.asaarep.sanctions.usecase.noiseword.port;

import ee.asaarep.sanctions.usecase.noiseword.DeleteNoiseWords;

public interface DeleteNoiseWordsPort {

  void delete(DeleteNoiseWords.Request request);
}
