package projectmanager.domain;

import lombok.*;
import projectmanager.infra.AbstractEvent;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class JwtValidated extends AbstractEvent {

    private String token;
    private boolean isValid;

    public JwtValidated(String token, boolean isValid) {
        this.token = token;
        this.isValid = isValid;
    }
}
