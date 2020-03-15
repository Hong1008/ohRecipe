package remasterWithJpa.ohRecipe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(StepId.class)
public class Step {
    @Id
    private Integer cookingNo;

    @Id
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private Primary primary;

    @Column(length = 4000)
    private String cookingDc;

    @Column(length = 4000)
    private String streStepImageUrl;

    @Column(length = 4000)
    private String stepTip;
}
