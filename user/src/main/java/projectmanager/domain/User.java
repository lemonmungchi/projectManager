package projectmanager.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import projectmanager.UserApplication;

@Entity
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String password; // ✅ 비밀번호 필드 추가

    private String role;

    @PostPersist
    public void onPostPersist() {
        UserRegistered userRegistered = new UserRegistered(this);
        userRegistered.publishAfterCommit();

        UserNotified userNotified = new UserNotified(this);
        userNotified.publishAfterCommit();

        UserLoginned userLoginned = new UserLoginned(this);
        userLoginned.publishAfterCommit();

        UserLogouted userLogouted = new UserLogouted(this);
        userLogouted.publishAfterCommit();
    }

    public static UserRepository repository() {
        return UserApplication.applicationContext.getBean(UserRepository.class);
    }

    // ✅ 불필요한 JWT 관련 메서드 삭제
}
