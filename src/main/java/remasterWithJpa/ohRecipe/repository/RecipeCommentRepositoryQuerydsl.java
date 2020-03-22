package remasterWithJpa.ohRecipe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import remasterWithJpa.ohRecipe.domain.code.BoardType;
import remasterWithJpa.ohRecipe.repository.dto.CommentViewDto;

public interface RecipeCommentRepositoryQuerydsl {

    Page<CommentViewDto> commentList(BoardType boardType, Long recipeId, Pageable pageable, String sort);
}
