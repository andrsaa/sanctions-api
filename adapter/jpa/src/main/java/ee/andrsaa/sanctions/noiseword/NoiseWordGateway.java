package ee.andrsaa.sanctions.noiseword;

import ee.andrsaa.sanctions.domain.noiseword.NoiseWord;
import ee.andrsaa.sanctions.domain.pageable.PagedResult;
import ee.andrsaa.sanctions.usecase.noiseword.DeleteNoiseWords;
import ee.andrsaa.sanctions.usecase.noiseword.FindNoiseWords;
import ee.andrsaa.sanctions.usecase.noiseword.SaveNoiseWords;
import ee.andrsaa.sanctions.usecase.noiseword.UpdateNoiseWords;
import ee.andrsaa.sanctions.usecase.noiseword.port.DeleteNoiseWordsPort;
import ee.andrsaa.sanctions.usecase.noiseword.port.FindNoiseWordsPort;
import ee.andrsaa.sanctions.usecase.noiseword.port.SaveNoiseWordsPort;
import ee.andrsaa.sanctions.util.PagedResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static ee.andrsaa.sanctions.util.SearchUtil.toPageRequest;

@Component
@RequiredArgsConstructor
public class NoiseWordGateway implements FindNoiseWordsPort, SaveNoiseWordsPort, DeleteNoiseWordsPort {
  private final NoiseWordRepository noiseWordRepository;

  @Override
  public PagedResult<NoiseWord> find(FindNoiseWords.Request request) {
    return PagedResultUtil.toPagedResult(noiseWordRepository.findAll(toPageRequest(request)),
      NoiseWordJpaEntity::toDomainEntity);
  }

  @Override
  public List<NoiseWord> findAll() {
    return noiseWordRepository.findAll().stream()
      .map(NoiseWordJpaEntity::toDomainEntity)
      .toList();
  }

  @Override
  public void save(SaveNoiseWords.Request request) {
    noiseWordRepository.saveAll(NoiseWordJpaEntity.fromDomainEntities(request.noiseWords()));
  }

  @Override
  public void update(UpdateNoiseWords.Request request) {
    var existing = noiseWordRepository.findAllById(request.noiseWords().stream()
      .map(NoiseWord::id)
      .toList());
    var updateResources = request.noiseWords().stream()
      .collect(Collectors.toMap(NoiseWord::id, r -> r));

    noiseWordRepository.saveAll(existing.stream()
      .map(entity -> updateResources.containsKey(entity.id()) ? entity.update(updateResources.get(entity.id())) : entity)
      .toList());
  }

  @Override
  public void delete(DeleteNoiseWords.Request request) {
    noiseWordRepository.deleteAllById(request.noiseWordsToDelete());
  }
}
