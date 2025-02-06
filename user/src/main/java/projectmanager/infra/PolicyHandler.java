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
    UserRepository userRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ValidationSuccessed'"
    )
    public void wheneverValidationSuccessed_ValidateUser(
        @Payload ValidationSuccessed validationSuccessed
    ) {
        ValidationSuccessed event = validationSuccessed;
        System.out.println(
            "\n\n##### listener ValidateUser : " + validationSuccessed + "\n\n"
        );

        // Sample Logic //
        User.validateUser(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='JwtDeleted'"
    )
    public void wheneverJwtDeleted_DeleteJwt(@Payload JwtDeleted jwtDeleted) {
        JwtDeleted event = jwtDeleted;
        System.out.println(
            "\n\n##### listener DeleteJwt : " + jwtDeleted + "\n\n"
        );

        // Sample Logic //
        User.deleteJwt(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
