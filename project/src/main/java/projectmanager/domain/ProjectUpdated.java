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
    private Integer taskCnt;
    private Integer completeTaskCnt;
    private String ownerId;
    private String isCompleted;
    private List<Long> userId;

    public ProjectUpdated(Project aggregate) {
        super(aggregate);
    }

    public ProjectUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
