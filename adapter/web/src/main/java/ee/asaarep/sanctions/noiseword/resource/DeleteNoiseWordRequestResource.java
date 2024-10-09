package ee.asaarep.sanctions.noiseword.resource;

import ee.asaarep.sanctions.usecase.noiseword.DeleteNoiseWords;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

@Schema(name = "DeleteNoiseWordRequest")
public record DeleteNoiseWordRequestResource(@Size(min = 1, max = 1000) Set<UUID> noiseWordsToDelete) {

  public DeleteNoiseWords.Request toRequest() {
    return DeleteNoiseWords.Request.of(noiseWordsToDelete);
  }
}
