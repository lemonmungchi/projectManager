package projectmanager.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import projectmanager.domain.*;

@Component
public class JwtHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Jwt>> {

    @Override
    public EntityModel<Jwt> process(EntityModel<Jwt> model) {
        return model;
    }
}
