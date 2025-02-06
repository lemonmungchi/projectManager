package projectmanager.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import projectmanager.JwtApplication;
import projectmanager.domain.JwtDeleted;
import projectmanager.domain.ValidationSuccessed;

@Entity
@Table(name = "Jwt_table")
@Data
//<<< DDD / Aggregate Root
public class Jwt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean isValidate;

    private String userId;

    public static JwtRepository repository() {
        JwtRepository jwtRepository = JwtApplication.applicationContext.getBean(
            JwtRepository.class
        );
        return jwtRepository;
    }

    //<<< Clean Arch / Port Method
    public static void validate(JwtGenerated jwtGenerated) {
        //implement business logic here:

        /** Example 1:  new item 
        Jwt jwt = new Jwt();
        repository().save(jwt);

        ValidationSuccessed validationSuccessed = new ValidationSuccessed(jwt);
        validationSuccessed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(jwtGenerated.get???()).ifPresent(jwt->{
            
            jwt // do something
            repository().save(jwt);

            ValidationSuccessed validationSuccessed = new ValidationSuccessed(jwt);
            validationSuccessed.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
