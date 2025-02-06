package projectmanager.domain;

import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

@Data
@ToString
public class ProjectDeleted extends AbstractEvent {

    private Long id;
    private String name;
    private String description;
    private String status;
    private String dueDate;
    private String taskCnt;
    private String completeTaskCnt;
}
