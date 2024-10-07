package ee.asaarep.sanctions.noiseword;

import ee.asaarep.sanctions.noiseword.resource.NoiseWordResultRow;
import ee.asaarep.sanctions.noiseword.resource.NoiseWordSaveResource;
import ee.asaarep.sanctions.usecase.noiseword.FindNoiseWords;
import ee.asaarep.sanctions.usecase.noiseword.SaveNoiseWords;
import ee.asaarep.sanctions.util.PageableUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/noise-word")
public class NoiseWordController {
  private final FindNoiseWords findNoiseWords;
  private final SaveNoiseWords saveNoiseWords;

  @GetMapping
  Page<NoiseWordResultRow> getNoiseWords(Pageable pageable) {
    return PageableUtil.present(findNoiseWords.execute(PageableUtil.toRequest(pageable, new FindNoiseWords.Request())),
      NoiseWordResultRow::new);
  }

  @PostMapping("/add")
  ResponseEntity<Void> addNoiseWords(@Valid @RequestBody NoiseWordSaveResource resource) {
    saveNoiseWords.execute(resource.toRequest());
    return ResponseEntity.ok().build();
  }
}
