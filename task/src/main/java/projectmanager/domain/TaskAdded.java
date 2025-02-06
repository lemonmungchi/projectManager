package projectmanager.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class TaskAdded extends AbstractEvent {

    private Long id;
    private String projectId;
    private String name;
    private String description;
    private String status;
    private String dueDate;

    public TaskAdded(Task aggregate) {
        super(aggregate);
    }

    public TaskAdded() {
        super();
    }
}
//>>> DDD / Domain Event
