package remasterWithJpa.ohRecipe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
//@SequenceGenerator(name = "irdnt_num", sequenceName = "irdnt_num", initialValue = 1,allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Irdnt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "irdnt_num")
    @Column(name = "irdnt_sn")
    private Long id;

    private String irdntCpcty;
    private String irdntTyNm;
    private String importance;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recipe_id")
    private Primary primary;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "irdnt_nm")
    private IrdntType irdntType;
}
