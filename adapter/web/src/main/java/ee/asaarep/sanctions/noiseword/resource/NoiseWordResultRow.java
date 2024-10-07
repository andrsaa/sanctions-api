package ee.asaarep.sanctions.noiseword.resource;

import ee.asaarep.sanctions.domain.noiseword.NoiseWord;
import lombok.Getter;

import java.util.UUID;

@Getter
public class NoiseWordResultRow {
  private final UUID id;
  private final String value;

  public NoiseWordResultRow(NoiseWord noiseWord) {
    this.id = noiseWord.id();
    this.value = noiseWord.value();
  }
}
