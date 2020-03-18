package remasterWithJpa.ohRecipe.repository.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import remasterWithJpa.ohRecipe.domain.Step;

@Getter
@ToString
public class StepViewDto {
    private int cookingNo;
    private String cookingDc;

    public StepViewDto(Step step) {
        this.cookingNo = step.getCookingNo();
        this.cookingDc = step.getCookingDc();
    }
}
