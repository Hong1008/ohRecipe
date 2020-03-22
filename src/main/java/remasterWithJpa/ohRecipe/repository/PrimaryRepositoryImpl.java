package remasterWithJpa.ohRecipe.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.core.types.dsl.BooleanTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import remasterWithJpa.ohRecipe.domain.Primary;
import remasterWithJpa.ohRecipe.domain.QIrdnt;
import remasterWithJpa.ohRecipe.domain.QPrimary;
import remasterWithJpa.ohRecipe.domain.code.RecipeType;
import remasterWithJpa.ohRecipe.repository.dto.PrimViewDto;

import java.util.ArrayList;
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
                .where(primary.id.in(irdntSelfJoinSub(irdntNms)),primary.recipeType.eq(RecipeType.p))
                .orderBy(primary.rating.desc().nullsLast())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        if(result.getTotal() <= 0){
            result = queryFactory
                    .select(Projections.constructor(PrimViewDto.class, primary))
                    .from(primary)
                    .where(primary.id.in(irdntSub(irdntNms)),primary.recipeType.eq(RecipeType.p))
                    .orderBy(primary.rating.desc().nullsLast())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();
        }

        return new PageImpl<>(result.getResults(),pageable,result.getTotal());
    }

    private JPQLQuery<Long> irdntSelfJoinSub(List<String> irdntNms) {
        JPQLQuery<Long> irdntBase = JPAExpressions
                .select(irdnt.primary.id)
                .from(irdnt);
        List<QIrdnt> irdnts = new ArrayList<>();
        irdnts.add(irdnt);
        BooleanBuilder eqIrdnts = new BooleanBuilder();

        for (int i = 0; i < irdntNms.size()-1; i++) {
            irdnts.add(new QIrdnt("irdntSelf"+i));
            irdntBase.join(irdnts.get(i+1));
            irdntBase.on(irdnts.get(i).primary.id.eq(irdnts.get(i+1).primary.id));
            eqIrdnts.and(irdnts.get(i).irdntType.irdntNm.eq(irdntNms.get(i)));
        }
        eqIrdnts.and(irdnts.get(irdnts.size()-1).irdntType.irdntNm.eq(irdntNms.get(irdntNms.size()-1)));
        irdntBase.where(eqIrdnts);

        return irdntBase;
    }

    private JPQLQuery<Long> irdntSub(List<String> irdntNms) {
        return JPAExpressions
                .select(irdnt.primary.id)
                .from(irdnt)
                .join(irdnt.primary)
                .where(inIrdntNm(irdntNms),irdnt.irdntTyNm.eq("주재료"));
    }

    private BooleanExpression inIrdntNm(List<String> irdntNms) {
        return irdntNms != null ? irdnt.irdntType.irdntNm.in(irdntNms) : null;
    }
}
