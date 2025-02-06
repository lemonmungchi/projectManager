package projectmanager.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class TaskDeleted extends AbstractEvent {

    private Long id;

    public TaskDeleted(Task aggregate) {
        super(aggregate);
    }

    public TaskDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
