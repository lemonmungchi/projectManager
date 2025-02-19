package projectmanager.domain;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Entity
@Table(name = "user_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonProperty("password") 
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    // ✅ 회원가입 후 이벤트 발생
    @PostPersist
    public void onPostPersist() {
        new UserRegistered(this).publishAfterCommit();
        new UserNotified(this).publishAfterCommit();
    }
}
