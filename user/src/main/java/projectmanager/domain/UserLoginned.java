package projectmanager.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

//<<< DDD / Domain Event
@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class UserLoginned extends AbstractEvent {

    private Long id;
    private String name;
    private String email;
    private Boolean isTokken;

    public UserLoginned(User aggregate) {
        super(aggregate);
    }

    public UserLoginned() {
        super();
    }
}
//>>> DDD / Domain Event
