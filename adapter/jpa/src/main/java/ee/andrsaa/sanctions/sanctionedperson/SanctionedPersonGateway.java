package ee.andrsaa.sanctions.sanctionedperson;

import ee.andrsaa.sanctions.domain.pageable.PagedResult;
import ee.andrsaa.sanctions.domain.sanctionedperson.SanctionedPerson;
import ee.andrsaa.sanctions.domain.sanctionedperson.SanctionedPersonSimilarity;
import ee.andrsaa.sanctions.usecase.sanctionedperson.CheckIfSanctioned;
import ee.andrsaa.sanctions.usecase.sanctionedperson.DeleteSanctionedPersons;
import ee.andrsaa.sanctions.usecase.sanctionedperson.FindSanctionedPersons;
import ee.andrsaa.sanctions.usecase.sanctionedperson.SaveSanctionedPersons;
import ee.andrsaa.sanctions.usecase.sanctionedperson.UpdateSanctionedPersons;
import ee.andrsaa.sanctions.usecase.sanctionedperson.port.DeleteSanctionedPersonPort;
import ee.andrsaa.sanctions.usecase.sanctionedperson.port.FindSanctionedPersonPort;
import ee.andrsaa.sanctions.usecase.sanctionedperson.port.SaveSanctionedPersonPort;
import ee.andrsaa.sanctions.util.PagedResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static ee.andrsaa.sanctions.util.SearchUtil.toPageRequest;

@Component
@RequiredArgsConstructor
public class SanctionedPersonGateway implements FindSanctionedPersonPort, SaveSanctionedPersonPort, DeleteSanctionedPersonPort {
  private final SanctionedPersonRepository sanctionedPersonRepository;
  private final SanctionedPersonSpecification sanctionedPersonSpecification = new SanctionedPersonSpecification();

  @Override
  public PagedResult<SanctionedPerson> findSanctionedPersons(FindSanctionedPersons.Request request) {
    return PagedResultUtil.toPagedResult(
      sanctionedPersonRepository.findAll(sanctionedPersonSpecification.of(request), toPageRequest(request)),
      SanctionedPersonJpaEntity::toDomainEntity);
  }

  @Override
  public SanctionedPersonSimilarity checkIfPersonIsSanctioned(CheckIfSanctioned.Request request) {
    return sanctionedPersonRepository.checkIfPersonIsSanctioned(request.fullName, request.similarityThreshold)
      .map(SanctionedPersonSimilarityProjection::toDomainEntity)
      .orElse(SanctionedPersonSimilarity.notSanctioned());
  }

  @Override
  public List<SanctionedPerson> save(SaveSanctionedPersons.Request request) {
    return sanctionedPersonRepository.saveAll(SanctionedPersonJpaEntity.fromDomainEntities(request.sanctionedPersons())).stream()
      .map(SanctionedPersonJpaEntity::toDomainEntity)
      .toList();
  }

  @Override
  public void update(UpdateSanctionedPersons.Request request) {
    var existing = sanctionedPersonRepository.findAllById(request.sanctionedPersons().stream()
      .map(SanctionedPerson::id)
      .toList());
    var updateResources = request.sanctionedPersons().stream()
      .collect(Collectors.toMap(SanctionedPerson::id, r -> r));

    sanctionedPersonRepository.saveAll(existing.stream()
      .map(entity -> updateResources.containsKey(entity.id()) ? entity.update(updateResources.get(entity.id())) : entity)
      .toList());
  }

  @Override
  public void delete(DeleteSanctionedPersons.Request request) {
    sanctionedPersonRepository.deleteAllById(request.sanctionedPersonsToDelete());
  }
}
