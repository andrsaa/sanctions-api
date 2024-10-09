package ee.andrsaa.sanctions.noiseword.resource;

import ee.andrsaa.sanctions.domain.noiseword.NoiseWord;
import ee.andrsaa.sanctions.usecase.noiseword.UpdateNoiseWords;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;


import java.util.List;
import java.util.Set;
import java.util.UUID;

@Schema(name = "UpdateNoiseWordRequest")
public record UpdateNoiseWordRequestResource(@Size(min = 1, max = 1000) Set<@Valid NoiseWordResource> noiseWords) {

  public UpdateNoiseWords.Request toRequest() {
    return UpdateNoiseWords.Request.of(toDomainEntities());
  }

  private List<NoiseWord> toDomainEntities() {
    return noiseWords.stream()
      .map(noiseWord -> NoiseWord.builder()
        .id(noiseWord.noiseWordToUpdateId())
        .value(noiseWord.value)
        .build())
      .toList();
  }

  @Schema(name = "UpdateNoiseWord")
  public record NoiseWordResource(
    @Nonnull UUID noiseWordToUpdateId,
    @Nonnull @Size(min = 1, max = 255) String value
  ) {
  }
}
