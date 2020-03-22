package remasterWithJpa.ohRecipe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import remasterWithJpa.ohRecipe.domain.RecipeComment;
import remasterWithJpa.ohRecipe.domain.code.BoardType;

import java.util.List;

public interface RecipeCommentRepository extends JpaRepository<RecipeComment,Long>, RecipeCommentRepositoryQuerydsl {

    @Query("select rc from RecipeComment rc " +
            "where rc.boardType = :boardType " +
            "and rc.primary.id = :id")
    Page<RecipeComment> findAllBy(
            @Param("boardType")BoardType boardType,
            @Param("id")Long recipeId, Pageable pageable);
}
