package projectmanager.domain;

import java.util.*;
import lombok.*;
import projectmanager.domain.*;
import projectmanager.infra.AbstractEvent;

@Data
@ToString
public class JwtGenerated extends AbstractEvent {

    private Long id;
    private String name;
    private String email;
    private Boolean isTokken;
}
