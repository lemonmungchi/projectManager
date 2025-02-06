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
    TaskRepository taskRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProjectDeleted'"
    )
    public void wheneverProjectDeleted_DeleteAllTasks(
        @Payload ProjectDeleted projectDeleted
    ) {
        ProjectDeleted event = projectDeleted;
        System.out.println(
            "\n\n##### listener DeleteAllTasks : " + projectDeleted + "\n\n"
        );

        // Sample Logic //
        Task.deleteAllTasks(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProjectDeleted'"
    )
    public void wheneverProjectDeleted_DeleteAllTasks(
        @Payload ProjectDeleted projectDeleted
    ) {
        ProjectDeleted event = projectDeleted;
        System.out.println(
            "\n\n##### listener DeleteAllTasks : " + projectDeleted + "\n\n"
        );

        // Sample Logic //
        Task.deleteAllTasks(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProjectCreated'"
    )
    public void wheneverProjectCreated_AddDefaultTask(
        @Payload ProjectCreated projectCreated
    ) {
        ProjectCreated event = projectCreated;
        System.out.println(
            "\n\n##### listener AddDefaultTask : " + projectCreated + "\n\n"
        );

        // Sample Logic //
        Task.addDefaultTask(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
