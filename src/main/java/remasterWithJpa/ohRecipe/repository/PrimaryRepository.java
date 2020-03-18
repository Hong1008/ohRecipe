package remasterWithJpa.ohRecipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remasterWithJpa.ohRecipe.domain.Primary;

public interface PrimaryRepository extends JpaRepository<Primary,Long>, PrimaryRepositoryQuerydsl {
}
