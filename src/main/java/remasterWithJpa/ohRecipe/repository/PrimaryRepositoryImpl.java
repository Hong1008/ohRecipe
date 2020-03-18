package remasterWithJpa.ohRecipe.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import remasterWithJpa.ohRecipe.domain.QIrdnt;
import remasterWithJpa.ohRecipe.domain.QPrimary;
import remasterWithJpa.ohRecipe.repository.dto.PrimViewDto;

import java.util.List;

import static remasterWithJpa.ohRecipe.domain.QIrdnt.irdnt;
import static remasterWithJpa.ohRecipe.domain.QPrimary.primary;

@RequiredArgsConstructor
public class PrimaryRepositoryImpl implements PrimaryRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PrimViewDto> viewResult(List<String> irdntNms, Pageable pageable) {

        QueryResults<PrimViewDto> result = queryFactory
                .select(Projections.constructor(PrimViewDto.class, primary))
                .from(primary)
                .where(primary.id.in(irdntSub(irdntNms)))
                .orderBy(primary.rating.desc().nullsLast())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(),pageable,result.getTotal());
    }

    private JPQLQuery<Long> irdntSub(List<String> irdntNms) {
        return JPAExpressions
                .select(irdnt.primary.id)
                .from(irdnt)
                .where(getIrdntNmIn(irdntNms));
    }

    private BooleanExpression getIrdntNmIn(List<String> irdntNms) {
        return irdntNms != null ? irdnt.irdntType.irdntNm.in(irdntNms) : null;
    }
}
