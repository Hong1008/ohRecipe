package remasterWithJpa.ohRecipe.repository.dto;

import lombok.Getter;
import lombok.ToString;
import remasterWithJpa.ohRecipe.domain.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class PrimViewDto {
    private Long recipeId;
    private String imgUrl;
    private String recipeNmKo;
    private String sumry;
    private String nationNm;
    private int hour;
    private String minute;
    private String calorie;
    private String levelNm;
    private List<IrdntViewDto> irdntList;
    private List<StepViewDto> stepList;

    public PrimViewDto(Primary primary) {
        this.recipeId = primary.getId();
        this.imgUrl = primary.getImgUrl();
        this.recipeNmKo = primary.getRecipeNmKo();
        this.sumry = primary.getSumry();
        this.nationNm = primary.getNationNm();
        int cookingRealTime = Integer.parseInt(primary.getCookingTime().replace("분", ""));
        this.hour = cookingRealTime/60;
        this.minute = cookingRealTime%60 < 10 ? "0"+cookingRealTime%60 : cookingRealTime%60+"";
        this.calorie = primary.getCalorie().replace("Kcal", "");
        this.levelNm = primary.getLevelNm();
        this.irdntList = primary.getIrdnts().stream().map(IrdntViewDto::new).collect(Collectors.toList());
        this.stepList = primary.getSteps().stream().map(StepViewDto::new).collect(Collectors.toList());
    }
}
