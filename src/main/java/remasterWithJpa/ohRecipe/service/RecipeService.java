package remasterWithJpa.ohRecipe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import remasterWithJpa.ohRecipe.controller.dto.PrimSearchDto;
import remasterWithJpa.ohRecipe.controller.dto.SearchCase;
import remasterWithJpa.ohRecipe.domain.Primary;
import remasterWithJpa.ohRecipe.repository.PrimaryQuerySupport;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeService {

    private final PrimaryQuerySupport primaryQuerySupport;

    public Page<Primary> sortView(PrimSearchDto primSearchDto, Pageable pageable){
        String recipeNmKo = null;
        String irdntNm = null;
        if(primSearchDto.isSearchExist()) {
            String sc = primSearchDto.getSearchCase();
            String sw = primSearchDto.getSearchWord();
            recipeNmKo = SearchCase.getSearchWord(SearchCase.recipeNmKo, sc, sw);
            irdntNm = SearchCase.getSearchWord(SearchCase.irdntNm, sc, sw);
        }

        return primaryQuerySupport.sortView(primSearchDto.getNationNm(),recipeNmKo,irdntNm,pageable);
    }


}
