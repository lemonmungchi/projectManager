package projectmanager.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import projectmanager.config.kafka.KafkaProcessor;
import projectmanager.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ProjectRepository projectRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TaskAdded'"
    )
    public void wheneverTaskAdded_UpdateTaskCnt(@Payload TaskAdded taskAdded) {
        TaskAdded event = taskAdded;
        System.out.println(
            "\n\n##### listener UpdateTaskCnt : " + taskAdded + "\n\n"
        );

        // Sample Logic //
        Project.updateTaskCnt(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TaskCompleted'"
    )
    public void wheneverTaskCompleted_UpdateCompleteTaskCnt(
        @Payload TaskCompleted taskCompleted
    ) {
        TaskCompleted event = taskCompleted;
        System.out.println(
            "\n\n##### listener UpdateCompleteTaskCnt : " +
            taskCompleted +
            "\n\n"
        );

        // Sample Logic //
        Project.updateCompleteTaskCnt(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
