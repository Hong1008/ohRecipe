package remasterWithJpa.ohRecipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import remasterWithJpa.ohRecipe.domain.Primary;
import remasterWithJpa.ohRecipe.domain.code.RecipeType;

import java.util.List;

public interface PrimaryRepository extends JpaRepository<Primary,Long>, PrimaryRepositoryQuerydsl {

    @Query("select distinct p.nationNm from Primary p where p.recipeType = :recipeType")
    List<String> findNationNms(@Param("recipeType") RecipeType recipeType);
}
