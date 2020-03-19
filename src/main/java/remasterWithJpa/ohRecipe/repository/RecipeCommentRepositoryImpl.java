package remasterWithJpa.ohRecipe.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import remasterWithJpa.ohRecipe.domain.QRecipeComment;
import remasterWithJpa.ohRecipe.domain.code.BoardType;
import remasterWithJpa.ohRecipe.repository.dto.CommentViewDto;

import static remasterWithJpa.ohRecipe.domain.QRecipeComment.recipeComment;

@RequiredArgsConstructor
public class RecipeCommentRepositoryImpl implements RecipeCommentRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CommentViewDto> commentList(BoardType boardType, Long recipeId, Pageable pageable) {
        queryFactory
                .select(Projections.constructor(CommentViewDto.class, recipeComment))
                .from(recipeComment)
                .join(recipeComment.userTable).fetchJoin()
                .where(eqBoardType(boardType),
                        eqRecipeId(recipeId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return null;
    }

    private BooleanExpression eqBoardType(BoardType boardType) {

        return null;
    }

    private BooleanExpression eqRecipeId(Long recipeId) {

        return null;
    }
}
