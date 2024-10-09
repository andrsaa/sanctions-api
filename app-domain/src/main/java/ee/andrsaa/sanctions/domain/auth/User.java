package ee.andrsaa.sanctions.domain.auth;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class User implements Serializable {
  private final String personName;
  private final boolean system;

  public static User system() {
    return User.builder().system(true).build();
  }

}
