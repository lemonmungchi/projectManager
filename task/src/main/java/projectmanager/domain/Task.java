package projectmanager.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import lombok.NonNull;
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

    // project 하위 파일은 task
    private Long projectId;

    private String name;

    private String description;

    private String status;

    private String dueDate;

    // 일단은 사용하지 않음
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

        /*

        상위 project 삭제 -> 하위 task 모두 삭제

        PostPersist, PrePersist 중에 뭘로 해야 하는지는 나중에 결정

         */
        // Example 2:  finding and process

        repository().findById(projectDeleted.getId()).ifPresent(task->{

            repository().delete(task);

            TaskDeleted taskDeleted = new TaskDeleted(task);
            taskDeleted.publishAfterCommit();

         });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void addDefaultTask(ProjectCreated projectCreated) {

        /*

        prjectId는 필히 projectCreated에서 받아오기 즉, task는 project의 일부분을 의미

        project 생성시 자동으로 생성되는 task, 그렇기 때문에 welcome! 과 같은 환영 문구로 작성

        dueDate의 경우, project의 생성 시간을 받아와야 하지만, 현재는 LocalDataTime을 이용해서 임시로 작성 추후 projectCreated.getDueDate() 변경

         */

        // 현재 날짜와 시간 가져오기
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDateTime = now.format(formatter);

        // Example 1:  new item
        Task task = new Task();

        task.setProjectId(projectCreated.getId());
        task.setName("환영합니다!");
        task.setDescription("현재 task를 수정하여 사용하거나, 새로운 task를 생성하십시오.");
        task.setDueDate(formattedDateTime);
        task.setStatus("프로젝트 생성이 성공하여 test task가 자동으로 생성되었습니다.");
        task.setPriority("test");

        repository().save(task);

        TaskAdded taskAdded = new TaskAdded(task);
        taskAdded.publishAfterCommit();

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

    // commands

    //<<< Clean Arch / Port Method
    public void addTask(AddTaskCommand addTaskCommand) {
        //implement business logic here:

        // 현재 날짜와 시간 가져오기
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDateTime = now.format(formatter);

        setName(addTaskCommand.getName());
        setDescription(addTaskCommand.getDescription());
        setPriority(addTaskCommand.getPriority());
        // projectId를 종속시킬 방법을 찾아야함
        setProjectId(addTaskCommand.getId());
        setDueDate(formattedDateTime);
        setStatus("task 생성 완료");

        TaskAdded taskAdded = new TaskAdded(this);
        taskAdded.publishAfterCommit();
    }

    public void updateTask(UpdateTaskCommand updateTaskCommand) {
        //implement business logic here:

        // 현재 날짜와 시간 가져오기
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDateTime = now.format(formatter);

        setStatus("task 수정 완료");

        setName(updateTaskCommand.getName());
        setDescription(updateTaskCommand.getDescription());
        setPriority(updateTaskCommand.getPriority());
        setDueDate(formattedDateTime);

        TaskUpdated taskUpdated = new TaskUpdated(this);
        taskUpdated.publishAfterCommit();
    }

}
//>>> DDD / Aggregate Root
