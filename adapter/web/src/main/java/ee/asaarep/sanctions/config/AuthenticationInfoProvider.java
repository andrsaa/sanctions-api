package ee.asaarep.sanctions.config;

import ee.asaarep.sanctions.domain.auth.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
class AuthenticationInfoProvider implements AuditorAware<User> {

  @NonNull
  @Override
  public Optional<User> getCurrentAuditor() {
    return coalesce(this::getAuthenticatedUser, () -> Optional.of(User.system()));
  }

  private Optional<User> getAuthenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof UserDetails) {
        return Optional.of(User.builder()
          .personName(((UserDetails) principal).getUsername())
          .build());
      }
    }
    return Optional.empty();
  }

  @SafeVarargs
  private static <T> Optional<T> coalesce(Supplier<Optional<T>>... suppliers) {
    return Stream.of(suppliers)
      .map(Supplier::get)
      .filter(Optional::isPresent)
      .findFirst()
      .orElse(Optional.empty());
  }
}
