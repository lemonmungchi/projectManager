package projectmanager.domain;

import lombok.*;
import projectmanager.infra.AbstractEvent;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class JwtDeleted extends AbstractEvent {

    private String token;

    public JwtDeleted(String token) {
        this.token = token;
    }
}
