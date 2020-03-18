package remasterWithJpa.ohRecipe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import remasterWithJpa.ohRecipe.repository.dto.PrimViewDto;

import java.util.List;

public interface PrimaryRepositoryQuerydsl {

    Page<PrimViewDto> viewResult(List<String> irdntNms, Pageable pageable);
}
