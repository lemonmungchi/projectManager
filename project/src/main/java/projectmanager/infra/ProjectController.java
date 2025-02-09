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
// @RequestMapping(value="/projects")
@Transactional
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    //프로젝트 수정
    @RequestMapping(
        value = "/projects/{id}/updateproject",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Project updateProject(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /project/updateProject  called #####");
        Optional<Project> optionalProject = projectRepository.findById(id);

        optionalProject.orElseThrow(() -> new Exception("No Entity Found"));
        Project project = optionalProject.get();
        project.updateProject();

        projectRepository.save(project);
        return project;
    }

    //프로젝트 삭제
    @RequestMapping(
        value = "/projects/{id}/deleteproject",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Project deleteProject(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /project/deleteProject  called #####");
        Optional<Project> optionalProject = projectRepository.findById(id);

        optionalProject.orElseThrow(() -> new Exception("No Entity Found"));
        Project project = optionalProject.get();
        project.deleteProject();

        projectRepository.save(project);
        return project;
    }
}
//>>> Clean Arch / Inbound Adaptor