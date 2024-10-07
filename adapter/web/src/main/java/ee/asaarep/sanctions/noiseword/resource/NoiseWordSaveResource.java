package ee.asaarep.sanctions.noiseword.resource;

import ee.asaarep.sanctions.usecase.noiseword.SaveNoiseWords;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record NoiseWordSaveResource(@Size(min = 1, max = 1000) Set<String> noiseWords) {

  public SaveNoiseWords.Request toRequest() {
    return SaveNoiseWords.Request.of(noiseWords);
  }
}
