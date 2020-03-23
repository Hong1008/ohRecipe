package remasterWithJpa.ohRecipe.domain.code;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum Nation {
    KOREAN("3020001","한식"),
    WESTERN("3020002","양식"),
    JAPANESE("3020003","일식"),
    CHINESE("3020004","중식"),
    SOUTHEAST("3020005","동남아"),
    FUSION("3020006","퓨전");

    private String nationCode;
    private String nationNm;

    Nation(String nationCode, String nationNm) {
        this.nationCode = nationCode;
        this.nationNm = nationNm;
    }

    public static List<String> getNationNms(){
        List<String> nationNms = new ArrayList<>();
        for (Nation nation : Nation.values()) {
            nationNms.add(nation.getNationNm());
        }
        return nationNms;
    }
}
