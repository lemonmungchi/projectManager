package projectmanager.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectmanager.service.JwtTokenProvider;

@RestController
@RequestMapping("/jwt")
public class JwtController {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ✅ JWT 생성 API
    @PostMapping("/generate")
    public ResponseEntity<String> generateToken(@RequestBody TokenRequest request) {
        String token = jwtTokenProvider.generateToken(request.getUserId(), request.getRole(), request.getEmail());
        return ResponseEntity.ok(token);
    }

    // ✅ JWT 검증 API
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestBody String token) {
        boolean isValid = jwtTokenProvider.validateToken(token);
        return ResponseEntity.ok(isValid);
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