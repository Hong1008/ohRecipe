package remasterWithJpa.ohRecipe.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import remasterWithJpa.ohRecipe.domain.Primary;
import remasterWithJpa.ohRecipe.domain.code.RecipeType;

import static remasterWithJpa.ohRecipe.domain.QIrdnt.irdnt;
import static remasterWithJpa.ohRecipe.domain.QPrimary.primary;

@Repository
public class PrimaryQuerySupport extends Querydsl4RepositorySupport {

    public PrimaryQuerySupport() {
        super(Primary.class);
    }

    public Page<Primary> sortView(String nationNm, String recipeNmKo, String irdntNm, Pageable pageable) {
        return applyPagination(pageable, query ->
                query.selectFrom(primary)
                        .where(eqNationNm(nationNm),
                                eqSearchCase(recipeNmKo, irdntNm),primary.recipeType.eq(RecipeType.p)));
    }

    private BooleanBuilder eqSearchCase(String recipeNmKo, String irdntNm) {
        BooleanBuilder searchCase = new BooleanBuilder();
        if(StringUtils.hasText(irdntNm) && StringUtils.hasText(recipeNmKo)){
            searchCase.and(containRecipeNmKo(recipeNmKo)).or(eqIrdntNm(irdntNm));
        }else{
            searchCase.and(containRecipeNmKo(recipeNmKo));
            searchCase.and(eqIrdntNm(irdntNm));
        }
        return searchCase;
    }

    private BooleanExpression eqIrdntNm(String irdntNm) {
        return StringUtils.hasText(irdntNm) ?
                primary.id.in(
                        JPAExpressions.select(irdnt.primary.id)
                                .from(irdnt)
                                .where(irdnt.irdntType.irdntNm.contains(irdntNm))
                ) : null;
    }

    private BooleanExpression containRecipeNmKo(String recipeNmKo) {
        return StringUtils.hasText(recipeNmKo) ?
                primary.recipeNmKo.contains(recipeNmKo) : null;
    }

    private BooleanExpression eqNationNm(String nationNm) {
        return StringUtils.hasText(nationNm) ?
                primary.nationNm.eq(nationNm) : null;
    }
}
