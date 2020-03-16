package remasterWithJpa.ohRecipe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@SequenceGenerator(name = "self_num_seq", sequenceName = "selfrecipe_self_num_seq", allocationSize = 1)
public class Selfrecipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(generator = "self_num_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "recipe_id")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "recipe_id")
    private Primary primary;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserTable userTable;

    private LocalDateTime selfDate;

    @ColumnDefault("0")
    private Integer selfViews;
}
