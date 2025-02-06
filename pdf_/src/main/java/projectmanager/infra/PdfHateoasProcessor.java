package projectmanager.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import projectmanager.domain.*;

@Component
public class PdfHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Pdf>> {

    @Override
    public EntityModel<Pdf> process(EntityModel<Pdf> model) {
        return model;
    }
}
