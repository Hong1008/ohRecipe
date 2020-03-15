package remasterWithJpa.ohRecipe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTable {
    @Id @Column(name = "user_id",length = 50)
    private String id;

    @Column(nullable = false, length = 100)
    private String userPw;

    @Column(nullable = false, unique = true, length = 30)
    private String userNickname;

    @Column(nullable = false)
    private LocalDate userBirthday;

    @Column(length = 100)
    private String userIcon;

    @Column(length = 100)
    private String kakaoId;
}
