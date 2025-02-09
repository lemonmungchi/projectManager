package projectmanager.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class AddTaskCommand {

    private Long id;
    private String projectId;
    private String name;
    private String description;
    private String status;
    private String dueDate;
    private String priority;
}