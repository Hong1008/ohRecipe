package remasterWithJpa.ohRecipe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import remasterWithJpa.ohRecipe.domain.code.BoardType;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@SequenceGenerator(name = "com_num_seq", sequenceName = "com_num_seq", allocationSize = 1)
public class RecipeComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "com_num_seq")
    @Column(name = "com_num")
    private Long id;

    @Column(length = 2000, nullable = false)
    private String comContent;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserTable userTable;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recipe_id")
    private Primary primary;

    private LocalDateTime comTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "review_num")
    private Review review;

    @ColumnDefault("0")
    private Integer rating;
}
