package projectmanager.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import projectmanager.TaskApplication;
import projectmanager.domain.TaskAdded;
import projectmanager.domain.TaskCompleted;
import projectmanager.domain.TaskDeleted;
import projectmanager.domain.TaskUpdated;

@Entity
@Table(name = "Task_table")
@Data
//<<< DDD / Aggregate Root
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String projectId;

    private String name;

    private String description;

    private String status;

    private String dueDate;

    private String assigneeId;

    private String priority;

    @PostPersist
    public void onPostPersist() {
        TaskAdded taskAdded = new TaskAdded(this);
        taskAdded.publishAfterCommit();

        TaskDeleted taskDeleted = new TaskDeleted(this);
        taskDeleted.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        TaskUpdated taskUpdated = new TaskUpdated(this);
        taskUpdated.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        TaskCompleted taskCompleted = new TaskCompleted(this);
        taskCompleted.publishAfterCommit();
    }

    public static TaskRepository repository() {
        TaskRepository taskRepository = TaskApplication.applicationContext.getBean(
            TaskRepository.class
        );
        return taskRepository;
    }

    //<<< Clean Arch / Port Method
    public static void deleteAllTasks(ProjectDeleted projectDeleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Task task = new Task();
        repository().save(task);

        TaskDeleted taskDeleted = new TaskDeleted(task);
        taskDeleted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(projectDeleted.get???()).ifPresent(task->{
            
            task // do something
            repository().save(task);

            TaskDeleted taskDeleted = new TaskDeleted(task);
            taskDeleted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void deleteAllTasks(ProjectDeleted projectDeleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Task task = new Task();
        repository().save(task);

        TaskDeleted taskDeleted = new TaskDeleted(task);
        taskDeleted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(projectDeleted.get???()).ifPresent(task->{
            
            task // do something
            repository().save(task);

            TaskDeleted taskDeleted = new TaskDeleted(task);
            taskDeleted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void addDefaultTask(ProjectCreated projectCreated) {
        //implement business logic here:

        /** Example 1:  new item 
        Task task = new Task();
        repository().save(task);

        TaskAdded taskAdded = new TaskAdded(task);
        taskAdded.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(projectCreated.get???()).ifPresent(task->{
            
            task // do something
            repository().save(task);

            TaskAdded taskAdded = new TaskAdded(task);
            taskAdded.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
