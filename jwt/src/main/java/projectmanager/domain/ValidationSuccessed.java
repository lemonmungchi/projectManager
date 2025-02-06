package projectmanager.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ValidationSuccessed extends AbstractEvent {

    private Long id;
    private Boolean isValidate;

    public ValidationSuccessed(Jwt aggregate) {
        super(aggregate);
    }

    public ValidationSuccessed() {
        super();
    }
}
//>>> DDD / Domain Event
