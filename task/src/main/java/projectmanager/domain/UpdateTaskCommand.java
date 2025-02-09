package projectmanager.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateTaskCommand {

    private Long id;
    private Long projectId;
    private String name;
    private String description;
    private String status;
    private String dueDate;
    private String assigneeId;
    private String priority;
}
