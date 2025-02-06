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

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TaskCompleted'"
    )
    public void wheneverTaskCompleted_SendMessage(
        @Payload TaskCompleted taskCompleted
    ) {
        TaskCompleted event = taskCompleted;
        System.out.println(
            "\n\n##### listener SendMessage : " + taskCompleted + "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TaskAdded'"
    )
    public void wheneverTaskAdded_SendMessage(@Payload TaskAdded taskAdded) {
        TaskAdded event = taskAdded;
        System.out.println(
            "\n\n##### listener SendMessage : " + taskAdded + "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TaskUpdated'"
    )
    public void wheneverTaskUpdated_SendMessage(
        @Payload TaskUpdated taskUpdated
    ) {
        TaskUpdated event = taskUpdated;
        System.out.println(
            "\n\n##### listener SendMessage : " + taskUpdated + "\n\n"
        );
        // Sample Logic //

    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PdfGenerated'"
    )
    public void wheneverPdfGenerated_SendMessage(
        @Payload PdfGenerated pdfGenerated
    ) {
        PdfGenerated event = pdfGenerated;
        System.out.println(
            "\n\n##### listener SendMessage : " + pdfGenerated + "\n\n"
        );
        // Sample Logic //

    }
}
//>>> Clean Arch / Inbound Adaptor
