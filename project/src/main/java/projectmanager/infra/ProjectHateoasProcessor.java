package projectmanager.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import projectmanager.domain.*;

@Component
public class ProjectHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Project>> {

    @Override
    public EntityModel<Project> process(EntityModel<Project> model) {
        return model;
    }
}
