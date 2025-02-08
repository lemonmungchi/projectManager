package projectmanager.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectmanager.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/tasks")
@Transactional
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping(
            value = "/tasks/addtask",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8"
    )
    public Task addTask(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AddTaskCommand addTaskCommand
    ) throws Exception {
        System.out.println("##### /task/addTask  called #####");
        Task task = new Task();
        task.addTask(addTaskCommand);
        taskRepository.save(task);
        return task;
    }
}
//>>> Clean Arch / Inbound Adaptor
