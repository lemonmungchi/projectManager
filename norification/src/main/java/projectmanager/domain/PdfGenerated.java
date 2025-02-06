package projectmanager.domain;

import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

@Data
@ToString
public class PdfGenerated extends AbstractEvent {

    private Long id;
    private String projectId;
    private String taskId;
    private String status;
    private String createdAt;
    private String completedAt;
    private String filePath;
}
