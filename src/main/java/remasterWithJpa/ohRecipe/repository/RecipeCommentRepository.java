package remasterWithJpa.ohRecipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remasterWithJpa.ohRecipe.domain.RecipeComment;

public interface RecipeCommentRepository extends JpaRepository<RecipeComment,Long> {
}
