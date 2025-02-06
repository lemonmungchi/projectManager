package projectmanager.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import projectmanager.UserApplication;
import projectmanager.domain.JwtGenerated;
import projectmanager.domain.UserLoginned;
import projectmanager.domain.UserLogouted;
import projectmanager.domain.UserNotified;
import projectmanager.domain.UserRegistered;

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

    private Boolean isTokken;

    private String role;

    @PostPersist
    public void onPostPersist() {
        UserRegistered userRegistered = new UserRegistered(this);
        userRegistered.publishAfterCommit();

        UserNotified userNotified = new UserNotified(this);
        userNotified.publishAfterCommit();

        JwtGenerated jwtGenerated = new JwtGenerated(this);
        jwtGenerated.publishAfterCommit();

        UserLoginned userLoginned = new UserLoginned(this);
        userLoginned.publishAfterCommit();

        UserLogouted userLogouted = new UserLogouted(this);
        userLogouted.publishAfterCommit();
    }

    public static UserRepository repository() {
        UserRepository userRepository = UserApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    //<<< Clean Arch / Port Method
    public static void validateUser(ValidationSuccessed validationSuccessed) {
        //implement business logic here:

        /** Example 1:  new item 
        User user = new User();
        repository().save(user);

        UserLoginned userLoginned = new UserLoginned(user);
        userLoginned.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(validationSuccessed.get???()).ifPresent(user->{
            
            user // do something
            repository().save(user);

            UserLoginned userLoginned = new UserLoginned(user);
            userLoginned.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void deleteJwt(JwtDeleted jwtDeleted) {
        //implement business logic here:

        /** Example 1:  new item 
        User user = new User();
        repository().save(user);

        UserLogouted userLogouted = new UserLogouted(user);
        userLogouted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(jwtDeleted.get???()).ifPresent(user->{
            
            user // do something
            repository().save(user);

            UserLogouted userLogouted = new UserLogouted(user);
            userLogouted.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
