package remasterWithJpa.ohRecipe.repository.dto;

import lombok.Getter;
import remasterWithJpa.ohRecipe.domain.IrdntType;

@Getter
public class IrdntTypeViewDto {
    private String irdntNm;
    private String typeNm;

    public IrdntTypeViewDto(IrdntType irdntType) {
        this.irdntNm = irdntType.getIrdntNm();
        this.typeNm = irdntType.getTypeNm();
    }
}
