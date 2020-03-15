package remasterWithJpa.ohRecipe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IrdntType {
    @Id
    private String irdntNm;

    private String typeNm;
}
