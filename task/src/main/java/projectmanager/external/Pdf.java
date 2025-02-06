package projectmanager.external;

import java.util.Date;
import lombok.Data;

@Data
public class Pdf {

    private Long id;
    private String projectId;
    private String taskId;
    private String status;
    private String createdAt;
    private String completedAt;
    private String filePath;
}
