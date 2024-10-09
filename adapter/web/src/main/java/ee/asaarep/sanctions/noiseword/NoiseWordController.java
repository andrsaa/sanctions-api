package ee.asaarep.sanctions.noiseword;

import ee.asaarep.sanctions.noiseword.resource.FindNoiseWordResponseResource;
import ee.asaarep.sanctions.noiseword.resource.SaveNoiseWordResource;
import ee.asaarep.sanctions.usecase.noiseword.FindNoiseWords;
import ee.asaarep.sanctions.usecase.noiseword.SaveNoiseWords;
import ee.asaarep.sanctions.util.PageableUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "NoiseWordController", description = "Managing noise words")
public class NoiseWordController {
  private final FindNoiseWords findNoiseWords;
  private final SaveNoiseWords saveNoiseWords;

  @Operation(summary = "Find noise words")
  @GetMapping
  Page<FindNoiseWordResponseResource> getNoiseWords(Pageable pageable) {
    return PageableUtil.present(findNoiseWords.execute(PageableUtil.toRequest(pageable, new FindNoiseWords.Request())),
      FindNoiseWordResponseResource::new);
  }

  @Operation(summary = "Add noise words")
  @PostMapping("/add")
  ResponseEntity<Void> addNoiseWords(@Valid @RequestBody SaveNoiseWordResource resource) {
    saveNoiseWords.execute(resource.toRequest());
    return ResponseEntity.ok().build();
  }
}
