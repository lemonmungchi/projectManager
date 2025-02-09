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

    private Integer taskCnt;            //형변환(String->Integer)

    private Integer completeTaskCnt;    //형변환(String->Integer)

    private String ownerId;

    @PrePersist
    public void onPrePersist() {
        this.status = "미완료";
        if (this.taskCnt == null) this.taskCnt = 0;
        if (this.completeTaskCnt == null) this.completeTaskCnt = 0;
    }

    @PostPersist
    public void onPostPersist() {
        ProjectCreated projectCreated = new ProjectCreated(this);
        projectCreated.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
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

    //추가된 task개수 관리
    public static void updateTaskCnt(TaskAdded taskAdded) {
    
        repository().findById(Long.valueOf(taskAdded.getProjectId())).ifPresent(project->{
            
            project.setTaskCnt(project.getTaskCnt()+1);
            repository().save(project);

         });
    }

    //완료된 task개수 관리
    public static void updateCompleteTaskCnt(TaskCompleted taskCompleted) {
        
        repository().findById(Long.valueOf(taskCompleted.getProjectId())).ifPresent(project->{
            
            project.setCompleteTaskCnt(project.getCompleteTaskCnt()+1);
            repository().save(project);

         });
    }

    public void updateProject() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProject'");
    }

    public void deleteProject() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProject'");
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
