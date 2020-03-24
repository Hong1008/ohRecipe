package remasterWithJpa.ohRecipe.controller.dto;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class PrimSearchDto {
    private String nationNm;
    private String searchWord;
    private String searchCase;

    public boolean isSearchExist(){
        return StringUtils.hasText(searchWord) && StringUtils.hasText(searchCase);
    }

}
