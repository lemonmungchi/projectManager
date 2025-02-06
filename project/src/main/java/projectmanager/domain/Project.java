package projectmanager.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import projectmanager.ProjectApplication;
import projectmanager.domain.ProjectCreated;
import projectmanager.domain.ProjectDeleted;
import projectmanager.domain.ProjectUpdated;

@Entity
@Table(name = "Project_table")
@Data
//<<< DDD / Aggregate Root
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String status;

    private String dueDate;

    private String taskCnt;

    private String completeTaskCnt;

    private String ownerId;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        projectmanager.external.Task task = new projectmanager.external.Task();
        // mappings goes here
        ProjectApplication.applicationContext
            .getBean(projectmanager.external.TaskService.class)
            .addTask(task);

        ProjectCreated projectCreated = new ProjectCreated(this);
        projectCreated.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        projectmanager.external.Pdf pdf = new projectmanager.external.Pdf();
        // mappings goes here
        ProjectApplication.applicationContext
            .getBean(projectmanager.external.PdfService.class)
            .showPdf(pdf);

        ProjectUpdated projectUpdated = new ProjectUpdated(this);
        projectUpdated.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        ProjectDeleted projectDeleted = new ProjectDeleted(this);
        projectDeleted.publishAfterCommit();
    }

    public static ProjectRepository repository() {
        ProjectRepository projectRepository = ProjectApplication.applicationContext.getBean(
            ProjectRepository.class
        );
        return projectRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateTaskCnt(TaskAdded taskAdded) {
        //implement business logic here:

        /** Example 1:  new item 
        Project project = new Project();
        repository().save(project);

        */

        /** Example 2:  finding and process
        

        repository().findById(taskAdded.get???()).ifPresent(project->{
            
            project // do something
            repository().save(project);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateCompleteTaskCnt(TaskCompleted taskCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Project project = new Project();
        repository().save(project);

        */

        /** Example 2:  finding and process
        

        repository().findById(taskCompleted.get???()).ifPresent(project->{
            
            project // do something
            repository().save(project);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
