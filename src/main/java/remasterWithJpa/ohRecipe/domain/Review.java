package remasterWithJpa.ohRecipe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id @GeneratedValue
    @Column(name = "review_num")
    private Long id;

    @Column(length = 30)
    private String reviewRate;

    @ColumnDefault("sysdate")
    private LocalDateTime reviewDate;

    @ColumnDefault("0")
    private Long reviewViews;

    @Column(length = 4000)
    private String reviewUrl;

    @Column(length = 200, nullable = false)
    private String reviewSubject;

    @Column(nullable = false)
    @Lob
    private String reviewContent;

    @ManyToOne(fetch = LAZY)
    @JoinColumns({
            @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id", nullable = false),
            @JoinColumn(name = "recipe_nm_ko", referencedColumnName = "recipeNmKo", nullable = false)
    })
    private Primary primary;

    @ManyToOne(fetch = LAZY)
    @JoinColumns({
            @JoinColumn(name = "user_id",referencedColumnName = "user_id", nullable = false),
            @JoinColumn(name = "user_nickname", referencedColumnName = "userNickname", nullable = false)
    })
    private UserTable userTable;
}
