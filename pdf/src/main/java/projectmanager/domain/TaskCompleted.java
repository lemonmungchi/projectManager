package projectmanager.domain;

import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

@Data
@ToString
public class TaskCompleted extends AbstractEvent {

    private Long id;
    private String projectId;
    private String name;
    private String description;
    private String status;
    private String dueDate;
}
