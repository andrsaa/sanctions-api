package ee.andrsaa.sanctions.sanctionedperson;

import ee.andrsaa.sanctions.usecase.sanctionedperson.FindSanctionedPersons;
import org.springframework.data.jpa.domain.Specification;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class SanctionedPersonSpecification {
  public Specification<SanctionedPersonJpaEntity> of(FindSanctionedPersons.Request request) {
    return (root, query, cb) -> {
      if (isBlank(request.fullName())) {
        return null;
      }
      return cb.like(cb.upper(root.get("fullName")), "%" + request.fullName().toUpperCase() + "%");
    };
  }
}
