package remasterWithJpa.ohRecipe.domain;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class StepId implements Serializable {

    private Long primary;
    private String cookingNo;
}
