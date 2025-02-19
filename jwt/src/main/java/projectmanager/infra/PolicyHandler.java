package projectmanager.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import projectmanager.config.kafka.KafkaProcessor;
import projectmanager.domain.JwtValidated;
import projectmanager.service.JwtTokenProvider;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    // ✅ JWT 삭제 이벤트 처리 (블랙리스트 등록)
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='JwtDeleted'"
    )
    public void handleJwtDeleted(@Payload String token) {
        System.out.println("🔴 JWT 삭제 요청 수신: " + token);
        jwtTokenProvider.invalidateToken(token); // ✅ 토큰 무효화
        System.out.println("✅ JWT 블랙리스트에 추가 완료");
    }

    // ✅ JWT 검증 이벤트 처리 (Kafka를 통한 중앙 인증)
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='JwtValidated'"
    )
    public void handleJwtValidation(@Payload String token) {
        boolean isValid = jwtTokenProvider.validateToken(token);
        JwtValidated event = new JwtValidated(token, isValid);
        event.publish(); // ✅ 검증 결과 이벤트 발행
        System.out.println("🟢 JWT 검증 완료: " + token + " → 유효: " + isValid);
    }
}
