package projectmanager.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ProjectUpdated extends AbstractEvent {

    private Long id;
    private String name;
    private String description;
    private String status;
    private String dueDate;
    private String taskCnt;
    private String completeTaskCnt;

    public ProjectUpdated(Project aggregate) {
        super(aggregate);
    }

    public ProjectUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
