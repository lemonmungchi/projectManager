package projectmanager.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "task", url = "${api.url.task}")
public interface TaskService {
    @RequestMapping(method = RequestMethod.POST, path = "/tasks")
    public void addTask(@RequestBody Task task);

    @RequestMapping(method = RequestMethod.PUT, path = "/tasks")
    public void updateTask(@RequestBody Task task);
}
