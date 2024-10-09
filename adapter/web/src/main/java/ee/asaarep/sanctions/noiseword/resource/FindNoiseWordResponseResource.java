package ee.asaarep.sanctions.noiseword.resource;

import ee.asaarep.sanctions.domain.noiseword.NoiseWord;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.UUID;

@Getter
@Schema(name = "FindNoiseWordResponse")
public class FindNoiseWordResponseResource {
  private final UUID id;
  private final String value;

  public FindNoiseWordResponseResource(NoiseWord noiseWord) {
    this.id = noiseWord.id();
    this.value = noiseWord.value();
  }
}
