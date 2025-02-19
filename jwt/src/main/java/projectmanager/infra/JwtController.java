package projectmanager.infra;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectmanager.service.JwtTokenProvider;
import java.util.logging.Logger;

@RestController
@RequestMapping("/jwt")
public class JwtController {
    private final JwtTokenProvider jwtTokenProvider;
    private static final Logger logger = Logger.getLogger(JwtController.class.getName());

    public JwtController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ✅ JWT 생성 API
    @PostMapping("/generate")
    public ResponseEntity<String> generateToken(@RequestBody TokenRequest request) {
        String userId = request.getUserId();
        String role = request.getRole();
        String email = request.getEmail();

        String token = jwtTokenProvider.generateToken(userId, role, email);
        return ResponseEntity.ok(token);  // 🟢 JSON으로 JWT 반환
    }

    // ✅ JWT 검증 API (에러 로깅 추가)
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String authHeader) {
        logger.info("🔍 JWT 검증 요청 수신");
        System.out.println(authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warning("🚨 요청 헤더 오류: Authorization 헤더 없음");
            return ResponseEntity.badRequest().body(false);
        }

        String token = authHeader.substring(7); // "Bearer " 제거
        boolean isValid = jwtTokenProvider.validateToken(token);

        if (isValid) {
            logger.info("✅ JWT 검증 성공");
            return ResponseEntity.ok(true);
        } else {
            logger.warning("❌ JWT 검증 실패");
            return ResponseEntity.status(401).body(false);
        }
    }



    // ✅ JWT 무효화 API (로그아웃)
    @PostMapping("/invalidate")
    public ResponseEntity<String> invalidateToken(@RequestBody String token) {
        jwtTokenProvider.invalidateToken(token);
        return ResponseEntity.ok("Token invalidated successfully.");
    }
}


class TokenRequest {
    private String userId;
    private String role;
    private String email;

    // ✅ 기본 생성자 추가
    public TokenRequest() {}

    public TokenRequest(String userId, String role, String email) {
        this.userId = userId;
        this.role = role;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }
}