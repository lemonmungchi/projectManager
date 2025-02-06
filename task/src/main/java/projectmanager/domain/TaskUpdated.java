package projectmanager.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class TaskUpdated extends AbstractEvent {

    private Long id;
    private String projectId;
    private String name;
    private String description;
    private String status;
    private String dueDate;

    public TaskUpdated(Task aggregate) {
        super(aggregate);
    }

    public TaskUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
