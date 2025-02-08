package projectmanager.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

//<<< DDD / Domain Event
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

    public PdfGenerated(Pdf aggregate) {
        super(aggregate);
    }

    public PdfGenerated() {
        super();
    }
}
//>>> DDD / Domain Event
