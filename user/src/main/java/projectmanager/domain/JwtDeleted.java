package projectmanager.domain;

import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

@Data
@ToString
public class JwtDeleted extends AbstractEvent {

    private Long id;
    private Boolean isValidate;
}
