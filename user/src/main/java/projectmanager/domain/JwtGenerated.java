package projectmanager.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class JwtGenerated extends AbstractEvent {

    private Long id;
    private String name;
    private String email;
    private Boolean isTokken;

    public JwtGenerated(User aggregate) {
        super(aggregate);
    }

    public JwtGenerated() {
        super();
    }
}
//>>> DDD / Domain Event
