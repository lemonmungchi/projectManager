package projectmanager.domain;

import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

@Data
@ToString
public class TaskAdded extends AbstractEvent {

    private Long id;
    private Long projectId;
    private String name;
    private String description;
    private String status;
    private String dueDate;
}
