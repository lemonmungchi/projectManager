package projectmanager.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import projectmanager.domain.*;

@Component
public class TaskHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Task>> {

    @Override
    public EntityModel<Task> process(EntityModel<Task> model) {
        return model;
    }
}
