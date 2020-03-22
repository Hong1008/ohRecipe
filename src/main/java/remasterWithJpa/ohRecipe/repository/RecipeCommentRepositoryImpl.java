package remasterWithJpa.ohRecipe.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.Querydsl;
import remasterWithJpa.ohRecipe.domain.QRecipeComment;
import remasterWithJpa.ohRecipe.domain.code.BoardType;
import remasterWithJpa.ohRecipe.repository.dto.CommentViewDto;

import static remasterWithJpa.ohRecipe.domain.QRecipeComment.recipeComment;

@RequiredArgsConstructor
public class RecipeCommentRepositoryImpl implements RecipeCommentRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CommentViewDto> commentList(BoardType boardType, Long recipeId, Pageable pageable, String sort) {

        PathBuilder pathBuilder = new PathBuilder(recipeComment.getType(),recipeComment.getMetadata());

        QueryResults<CommentViewDto> results = queryFactory
                .select(Projections.constructor(CommentViewDto.class, recipeComment))
                .from(recipeComment)
                .join(recipeComment.userTable).fetchJoin()
                .where(eqBoardType(boardType),
                        eqRecipeId(recipeId))
                .orderBy(new OrderSpecifier(Order.DESC,pathBuilder.get(sort)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(),pageable,results.getTotal());
    }

    private BooleanExpression eqBoardType(BoardType boardType) {
        return recipeComment.boardType.eq(boardType);
    }

    private BooleanExpression eqRecipeId(Long recipeId) {
        return recipeComment.primary.id.eq(recipeId);
    }
}
