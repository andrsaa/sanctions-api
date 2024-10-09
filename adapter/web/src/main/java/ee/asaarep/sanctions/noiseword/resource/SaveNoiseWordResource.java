package ee.asaarep.sanctions.noiseword.resource;

import ee.asaarep.sanctions.usecase.noiseword.SaveNoiseWords;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Schema(name = "SaveNoiseWord")
public record SaveNoiseWordResource(@Size(min = 1, max = 1000) Set<String> noiseWords) {

  public SaveNoiseWords.Request toRequest() {
    return SaveNoiseWords.Request.of(noiseWords);
  }
}
