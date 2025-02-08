package projectmanager.domain;

import lombok.*;
import projectmanager.infra.AbstractEvent;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class JwtGenerated extends AbstractEvent {

    private String token;

    public JwtGenerated(String token) {
        this.token = token;
    }
}
