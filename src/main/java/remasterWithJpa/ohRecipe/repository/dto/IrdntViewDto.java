package remasterWithJpa.ohRecipe.repository.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import remasterWithJpa.ohRecipe.domain.Irdnt;

@Getter
@ToString
public class IrdntViewDto {
    private String irdntNm;
    private String importance;
    private String irdntTyNm;

    public IrdntViewDto(Irdnt irdnt) {
        this.irdntNm = irdnt.getIrdntType().getIrdntNm();
        this.importance = irdnt.getImportance();
        this.irdntTyNm = irdnt.getIrdntTyNm();
    }
}
