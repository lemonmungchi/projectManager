package projectmanager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "jwt-service", url = "http://jwt-service:8082/jwt")  // JWT 서비스 호출
public interface JwtClient {

    // ✅ JWT 토큰 생성
    @PostMapping("/generate")
    String generateToken(@RequestBody Map<String, String> requestBody);

    // ✅ JWT 검증
    @PostMapping("/validate")
    Boolean validateToken(@RequestHeader("Authorization") String token);

    // ✅ 로그아웃 (JWT 무효화)
    @PostMapping("/invalidate")
    String invalidateToken(@RequestBody Map<String, String> requestBody);
}
