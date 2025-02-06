package projectmanager.external;

import java.util.Date;
import lombok.Data;

@Data
public class Task {

    private Long id;
    private String projectId;
    private String name;
    private String description;
    private String status;
    private String dueDate;
    private String assigneeId;
    private String priority;
}
