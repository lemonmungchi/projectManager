package projectmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthService {
    private final WebClient webClient;

    public AuthService() {
        this.webClient = WebClient.builder()
            .baseUrl("http://jwt-service") // JWT 서비스의 주소
            .build();
    }

    // ✅ 로그인 시 JWT 토큰 요청
    public Mono<String> generateToken(String userId) {
        return webClient.post()
            .uri("/generate")
            .bodyValue(userId)
            .retrieve()
            .bodyToMono(String.class);
    }

    // ✅ 토큰 검증 요청
    public Mono<Boolean> validateToken(String token) {
        return webClient.post()
            .uri("/validate")
            .bodyValue(token)
            .retrieve()
            .bodyToMono(Boolean.class);
    }

    // ✅ 로그아웃 시 JWT 삭제 요청
    public Mono<String> invalidateToken(String token) {
        return webClient.post()
            .uri("/invalidate")
            .bodyValue(token)
            .retrieve()
            .bodyToMono(String.class);
    }
}
