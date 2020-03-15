package remasterWithJpa.ohRecipe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import remasterWithJpa.ohRecipe.domain.code.RecipeType;

import javax.persistence.*;

@Entity
@Getter
@SequenceGenerator(name = "pri_num", sequenceName = "pri_num", initialValue = 1,allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "primarys")
public class Primary {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pri_num")
    @Column(name = "recipe_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String recipeNmKo;

    @Column(length = 100, nullable = false)
    private String sumry;

    private String nationCode;
    private String nationNm;

    private String tyCode;
    private String tyNm;

    private String cookingTime;

    private String calorie;

    private String qnt;

    private String levelNm;

    private String irdntCode;

    private String pcNm;

    @Column(length = 4000)
    private String imgUrl;
    @Column(length = 4000)
    private String detUrl;

    @ColumnDefault("0")
    private double rating;

    @ColumnDefault("0")
    private Integer primViews;

    @Enumerated(EnumType.STRING)
    private RecipeType recipeType;
}
