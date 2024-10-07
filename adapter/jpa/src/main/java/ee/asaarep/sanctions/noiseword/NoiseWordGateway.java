package ee.asaarep.sanctions.noiseword;

import ee.asaarep.sanctions.domain.noiseword.NoiseWord;
import ee.asaarep.sanctions.domain.pageable.PagedResult;
import ee.asaarep.sanctions.usecase.noiseword.FindNoiseWords;
import ee.asaarep.sanctions.usecase.noiseword.SaveNoiseWords;
import ee.asaarep.sanctions.usecase.noiseword.port.FindNoiseWordsPort;
import ee.asaarep.sanctions.usecase.noiseword.port.SaveNoiseWordsPort;
import ee.asaarep.sanctions.util.PagedResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static ee.asaarep.sanctions.util.SearchUtil.toPageRequest;

@Component
@RequiredArgsConstructor
public class NoiseWordGateway implements FindNoiseWordsPort, SaveNoiseWordsPort {
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
}
