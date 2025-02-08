package projectmanager.infra;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import projectmanager.config.kafka.KafkaProcessor;
import projectmanager.domain.User;
import projectmanager.domain.UserRepository;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    UserRepository userRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    // ✅ 불필요한 JWT 관련 이벤트 리스너 제거
}
