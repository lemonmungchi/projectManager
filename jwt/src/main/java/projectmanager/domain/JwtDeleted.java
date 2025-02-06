package projectmanager.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class JwtDeleted extends AbstractEvent {

    private Long id;
    private Boolean isValidate;

    public JwtDeleted(Jwt aggregate) {
        super(aggregate);
    }

    public JwtDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
