package projectmanager.domain;

import lombok.*;
import projectmanager.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
@EqualsAndHashCode(callSuper = true) // ✅ 부모 클래스 고려
public class ValidationSuccessed extends AbstractEvent {

    private Long id;
    private Boolean isValidate;

    public ValidationSuccessed(Long id, Boolean isValidate) {
        this.id = id;
        this.isValidate = isValidate;
    }

    public ValidationSuccessed() {
        super();
    }
}
//>>> DDD / Domain Event
